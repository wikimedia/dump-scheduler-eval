import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


@XmlRootElement(namespace="http://www.mediawiki.org/xml/export-0.10/")
@XmlType(propOrder={"siteinfo", "page"})
public class Mediawiki {

    Siteinfo siteinfo;
    Page page;

    String version;
    String lang;
    
    public Siteinfo getSiteinfo() {
	return siteinfo;
    }

    public Page getPage() {
        return page;
    }

    public String getVersion() {
        return version;
    }

    public String getLang() {
        return lang;
    }

    @XmlElement
    public void setSiteinfo(Siteinfo siteinfo) {
        this.siteinfo = siteinfo;
    }

    @XmlElement
    public void setPage(Page page) {
        this.page = page;
    }

    @XmlAttribute
    public void setVersion(String version) {
        this.version = version;
    }

    @XmlAttribute(namespace="http://www.w3.org/XML/1998/namespace")
    public void setLang(String lang) {
        this.lang = lang;
    }
} 
