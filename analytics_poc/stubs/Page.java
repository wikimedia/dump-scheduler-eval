import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

@XmlRootElement
@XmlType(propOrder={"title", "ns", "id", "revision"})
// @XmlAccessorType(XmlAccessType.FIELD)
public class Page {

    Integer id;
    String title;
    Integer ns;
    Revision revision;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getNs() {
        return ns;
    }

    public Revision getRevision() {
        return revision;
    }

    @XmlElement
    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement
    public void setNs(Integer ns) {
        this.ns = ns;
    }

    @XmlElement
    public void setRevision(Revision revision) {
        this.revision = revision;
    }
} 

