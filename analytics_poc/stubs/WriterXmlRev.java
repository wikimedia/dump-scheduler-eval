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


public class WriterXmlRev {
    public static void main(String[] args) throws FileNotFoundException, SQLException, IOException, CharacterCodingException, ClassNotFoundException {
	String field = null;

	GetterSiteinfo getterSite = new GetterSiteinfo();

	Mediawiki mw = new Mediawiki();
	mw.setSiteinfo(getterSite.getSiteinfo());
	mw.setVersion("0.10");
	// fixme we need to get this from some nice place, it's not
	// always the first part of the wikiname is it? or...? better check this
	mw.setLang("el");

	Page page = new Page();
	GetterPage getterPage = new GetterPage();
	ResultSet pageResults = getterPage.getPage(223276);
	while (pageResults.next()) {
	    field = getterPage.cleanupField("PAGE_ID", pageResults);
	    page.setId(Integer.parseInt(field));
	    field = getterPage.cleanupField("PAGE_TITLE", pageResults);
	    page.setTitle(field);
	    field = getterPage.cleanupField("PAGE_NAMESPACE", pageResults);
	    page.setNs(Integer.parseInt(field));
	    mw.setPage(page);

	    GetterRev getterRev = new GetterRev();
	    Revision rev = new Revision();
	    ResultSet revResults = getterRev.getRevision(2700027);
	    while (revResults.next()) {
		field = getterRev.cleanupField("REV_ID", revResults);
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
		// fixme this must be a list of revisions now
		page.setRevision(rev);
	    }
	}
	// fixme do we really have to generate the whole thing in
	// memory first? can't be right
	try {
	    File file = new File("myrev.xml");
	    JAXBContext jaxbContext = JAXBContext.newInstance(Mediawiki.class);
	    NamespacePrefixMapper mapper = new NamespacePrefixMapper() {
		    public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
			// if I use "" then it ignores the empty string and puts ns2
			// note that it still puts ns3 for the schema instance and schema location links
			return "mw";
		    }
		};
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	    // jaxbMarshaller.setProperty("com.sun.xml.bind.indentString", "  ");
	    // output pretty printed
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
				       "http://www.mediawiki.org/xml/export-0.10/ http://www.mediawiki.org/xml/export-0.10.xsd");
	    jaxbMarshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", mapper);
	    jaxbMarshaller.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
	    jaxbMarshaller.marshal(mw, file);
	    jaxbMarshaller.marshal(mw, System.out);
	} catch (JAXBException e) {
	    e.printStackTrace();
	}
    }
}
