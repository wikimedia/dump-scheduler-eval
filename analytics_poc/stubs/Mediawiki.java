import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(namespace = "http://www.mediawiki.org/xml/export-0.10/")
@XmlType(propOrder = {"siteinfo", "page"})
public class Mediawiki {

    Siteinfo siteinfo;
    List<Page> page;

    String version;
    String lang;
    
    public Siteinfo getSiteinfo() {
        return siteinfo;
    }

    @XmlElement(name = "page")
    public List<Page> getPage() {
        if (page == null) {
            page = new ArrayList<Page>();
        }
        return this.page;
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

    @XmlAttribute
    public void setVersion(String version) {
        this.version = version;
    }

    @XmlAttribute(namespace = "http://www.w3.org/XML/1998/namespace")
    public void setLang(String lang) {
        this.lang = lang;
    }
} 
