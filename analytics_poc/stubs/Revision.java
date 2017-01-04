import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


@XmlRootElement
@XmlType(propOrder={"id", "timestamp", "contributor", "comment", "minor", "model", "format", "text", "sha1"})
// @XmlAccessorType(XmlAccessType.FIELD)
public class Revision {

    int id;
    String timestamp;
    Contributor contributor;
    Comment comment;
    Text text;
    String sha1;
    String model;
    String format;
    Empty minor;

    public int getId() {
        return id;
    }

    public Text getText() {
        return text;
    }

    public Comment getComment() {
        return comment;
    }

    public Contributor getContributor() {
        return contributor;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Empty getMinor() {
        return minor;
    }

    public String getSha1() {
        return sha1;
    }

    public String getModel() {
        return model;
    }

    public String getFormat() {
        return format;
    }

    @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public void setText(Text text) {
        this.text = text;
    }

    @XmlElement
    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @XmlElement
    public void setContributor(Contributor contributor) {
        this.contributor = contributor;
    }

    @XmlElement
    public void setTimestamp(String tstamp) {
        this.timestamp = tstamp;
    }

    @XmlElement(required=false)
    public void setMinor(Empty minor) {
        this.minor = minor;
    }

    @XmlElement
    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    @XmlElement
    public void setModel(String model) {
        this.model = model;
    }

    @XmlElement
    public void setFormat(String format) {
        this.format = format;
    }
} 

