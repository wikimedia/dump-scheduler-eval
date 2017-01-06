import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
@XmlType(propOrder = {"id", "bytes"})
public class Text {

    int id;
    int bytes;
    String deleted;

    public int getId() {
        return id;
    }

    public int getBytes() {
        return bytes;
    }

    public String getDeleted() {
        return deleted;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    @XmlAttribute
    public void setBytes(int bytes) {
        this.bytes = bytes;
    }

    @XmlAttribute
    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
} 

