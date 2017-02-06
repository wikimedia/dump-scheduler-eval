package org.wikimedia.dumps.stubs;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.CharacterCodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javanet.staxutils.IndentingXMLStreamWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.wikimedia.dumps.stubs.writers.WriterXmlRev;
import org.wikimedia.dumps.stubs.models.Revision;
import org.wikimedia.dumps.stubs.models.Siteinfo;
import org.wikimedia.dumps.stubs.models.Mediawiki;
import org.wikimedia.dumps.stubs.models.Empty;
import org.wikimedia.dumps.stubs.readers.GetterSiteinfo;
import org.wikimedia.dumps.stubs.readers.GetterPage;

public class WriterXmlStubs {
    public static Marshaller jaxbMarshaller;

    public static Marshaller getMarshaller(Class<?> someclass)
                throws FileNotFoundException, SQLException, IOException,
                CharacterCodingException, ClassNotFoundException, XMLStreamException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(someclass);
            NamespacePrefixMapper mapper = new NamespacePrefixMapper() {
                public String getPreferredPrefix(String namespaceUri,
                        String suggestion, boolean requirePrefix) {
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

    public static void usage(String message) {
        System.out.println(message);
        System.out.println("usage: java -cp classpath-here "
            + "WriterXmlStubs url-to-mw-api path-to-xml-output ");
        System.out.println("example: java -cp classpath-here WriterXmlStubs "
            + "http://localhost/elwikt/api.php mydump.xml");
        System.exit(1);
    }

    public static void main(String[] args)
                throws FileNotFoundException, SQLException, IOException,
                CharacterCodingException, ClassNotFoundException, XMLStreamException {
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

        XMLStreamWriter writerStdout = XMLOutputFactory.newFactory()
                .createXMLStreamWriter(System.out);
        XMLStreamWriter writerFile = XMLOutputFactory.newFactory()
                .createXMLStreamWriter(fileStream);
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
            writer.writeAttribute("http://www.w3.org/2001/XMLSchema-instance", "schemaLocation",
                "http://www.mediawiki.org/xml/export-0.10/ http://www.mediawiki.org/xml/export-0.10.xsd");
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
            WriterXmlRev.writePage(getterPage, pageResults, writers, jaxbMarshallerRev);
        }

        // end mediawiki
        for (XMLStreamWriter writer: writers) {
            writer.writeEndElement();
            writer.flush();
        }
    }
}
    
