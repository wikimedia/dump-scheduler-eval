import java.io.PrintStream;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.Marshaller;
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.sql.ResultSet;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.io.IOException;
import java.nio.charset.CharacterCodingException;
import java.util.List;
import java.util.ArrayList;
import net.sf.json.JSONObject;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.namespace.NamespaceContext;
import javanet.staxutils.IndentingXMLStreamWriter;
import java.util.Iterator;

public class WriterXmlRev {
    public static Marshaller jaxbMarshaller;

    public static Marshaller getMarshaller(Class someclass) throws FileNotFoundException, SQLException, IOException, CharacterCodingException, ClassNotFoundException, XMLStreamException {
	try {
	    JAXBContext jaxbContext = JAXBContext.newInstance(someclass);
	    NamespacePrefixMapper mapper = new NamespacePrefixMapper() {
		    public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
			// check if this is still true after this latest refactor
			// if I use "" then it ignores the empty string and puts ns2
			// note that it still puts ns3 for the schema instance and schema location links
			return "mw";
		    }
		};
	    jaxbMarshaller = jaxbContext.createMarshaller();
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
	    jaxbMarshaller.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
	} catch (JAXBException e) {
	    e.printStackTrace();
	}
	return jaxbMarshaller;
    }

    public static void writePage(GetterPage getterPage, ResultSet pageResults, List<XMLStreamWriter> writers, Marshaller jaxbMarshallerRev) throws SQLException, XMLStreamException, FileNotFoundException, IOException, CharacterCodingException, ClassNotFoundException {
	Page page = new Page();
	String field = getterPage.cleanupField("PAGE_ID", pageResults);
	page.setId(Integer.parseInt(field));
	field = getterPage.cleanupField("PAGE_TITLE", pageResults);
	page.setTitle(field);
	field = getterPage.cleanupField("PAGE_NAMESPACE", pageResults);
	page.setNs(Integer.parseInt(field));

	writePagePartial(page, writers);

	GetterRev getterRev = new GetterRev();
	ResultSet revResults = getterRev.getRevisionsForPage(page.getId());
	while (revResults.next()) {
	    // rev contents
	    writeRevision(getterRev, revResults, jaxbMarshallerRev, writers);
	}
	// end page
	for (XMLStreamWriter writer: writers) {
	    writer.writeEndElement();
	}
    }

    public static void writePagePartial(Page page, List<XMLStreamWriter> writers) throws XMLStreamException {
	for (XMLStreamWriter writer: writers) {
	    // start page
	    writer.writeStartElement("page");
	    // page contents except for revs
	    writer.writeStartElement("title");
	    writer.writeCharacters(page.getTitle());
	    writer.writeEndElement();
	    writer.writeStartElement("ns");
	    writer.writeCharacters(Integer.toString(page.getNs()));
	    writer.writeEndElement();
	    writer.writeStartElement("id");
	    writer.writeCharacters(Integer.toString(page.getId()));
	    writer.writeEndElement();
	    writer.flush();
	}
    }

    public static void writeRevision(GetterRev getterRev, ResultSet revResults, Marshaller jaxbMarshaller, List<XMLStreamWriter> writers) throws SQLException, CharacterCodingException {
	Revision rev = new Revision();
	String field = getterRev.cleanupField("REV_ID", revResults);
	rev.setId(Integer.parseInt(field));
	field = getterRev.cleanupField("REV_COMMENT", revResults);
	rev.setComment(field);
	Text text = new Text();
	field = getterRev.cleanupField("REV_TEXT_ID", revResults);
	text.setId(Integer.parseInt(field));
	field = getterRev.cleanupField("REV_LEN", revResults);
	text.setBytes(Integer.parseInt(field));
	Contributor contr = new Contributor();
	field = getterRev.cleanupField("REV_USER", revResults);
	contr.setId(Integer.parseInt(field));
	field = getterRev.cleanupField("REV_USER_TEXT", revResults);
	contr.setUsername(field);
	field = getterRev.cleanupField("REV_TIMESTAMP", revResults);
	rev.setTimestamp(field);
	field = getterRev.cleanupField("REV_MINOR_EDIT", revResults);
	Integer isMinor = Integer.parseInt(field);
	if (isMinor > 0) {
	    rev.setMinor(new Empty());
	}
	field = getterRev.cleanupField("REV_SHA1", revResults);
	rev.setSha1(field);
	try {
	    field = getterRev.cleanupField("REV_CONTENT_MODEL", revResults);
	    rev.setModel(field);
	} catch (NullPointerException e) {
	    // what is the null placeholder here?
	    rev.setModel("some_default_FIXME");
	}
	try {
	    field = getterRev.cleanupField("REV_CONTENT_FORMAT", revResults);
	    rev.setFormat(field);
	} catch (NullPointerException e) {
	    // what is the null placeholder here?
	    rev.setFormat("some_default_FIXME");
	}
	rev.setContributor(contr);
	rev.setText(text);
	for (XMLStreamWriter writer: writers) {
	    try {
		jaxbMarshaller.marshal(rev, writer);
	    } catch (JAXBException e) {
		e.printStackTrace();
	    }
	}
    }


    public static void usage(String message) {
	System.out.println(message);
	System.out.println("usage: java -cp classpath-here WriterXmlRev url-to-mw-api path-to-xml-output");
	System.out.println("example: java -cp classpath-here WriterXmlRev http://localhost/elwikt/api.php mydump.xml");
	System.exit(1);
    }

    public static void main(String[] args) throws FileNotFoundException, SQLException, IOException, CharacterCodingException, ClassNotFoundException, XMLStreamException {
	if (args.length < 1) {
	    usage("no api url specified");
	} else if (args.length < 2) {
	    usage("no xml output filename specified");
	}

	String apiUrl = args[0];
	String outputfile = args[1];
	Marshaller jaxbMarshallerRev = getMarshaller(Revision.class);
	Marshaller jaxbMarshallerSiteinfo = getMarshaller(Siteinfo.class);
	File file = new File(outputfile);
	PrintStream fileStream = new PrintStream(file);
	String field = null;
	GetterSiteinfo getterSite = new GetterSiteinfo(apiUrl);
	Mediawiki mw = new Mediawiki();

	mw.setSiteinfo(getterSite.getSiteinfo());
	mw.setVersion("0.10");
	mw.setLang(getterSite.getLang());

	XMLStreamWriter writerStdout = XMLOutputFactory.newFactory().createXMLStreamWriter(System.out);
	XMLStreamWriter writerFile = XMLOutputFactory.newFactory().createXMLStreamWriter(fileStream);
	writerStdout = new IndentingXMLStreamWriter(writerStdout);
	writerFile = new IndentingXMLStreamWriter(writerFile);
	List<XMLStreamWriter> writers = new ArrayList<XMLStreamWriter>();
	writers.add(writerStdout);
	writers.add(writerFile);

	for (XMLStreamWriter writer: writers) {
	    writer.setDefaultNamespace("http://www.mediawiki.org/xml/export-0.10/");
	}
	// fixme still have that annoying "mw" in there, can we lose it?
	for (XMLStreamWriter writer: writers) {
	    writer.writeStartElement("mw", "mediawiki", "http://www.mediawiki.org/xml/export-0.10/");
	    writer.writeNamespace("mw", "http://www.mediawiki.org/xml/export-0.10/");
	    writer.writeNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
	    writer.writeAttribute("http://www.w3.org/2001/XMLSchema-instance", "schemaLocation", "http://www.mediawiki.org/xml/export-0.10/ http://www.mediawiki.org/xml/export-0.10.xsd");
	    writer.writeAttribute("version", mw.getVersion());
	    writer.writeAttribute("xml:lang", mw.getLang());
	    try {
		jaxbMarshallerSiteinfo.marshal(mw.getSiteinfo(), writer);
	    } catch (JAXBException e) {
		e.printStackTrace();
	    }
	}

	GetterPage getterPage = new GetterPage();
	ResultSet pageResults = getterPage.getPages(223260,223280);
	while (pageResults.next()) {
	    writePage(getterPage, pageResults, writers, jaxbMarshallerRev);
	}

	// end mediawiki
	for (XMLStreamWriter writer: writers) {
	    writer.writeEndElement();
	    writer.flush();
	}
    }
}
