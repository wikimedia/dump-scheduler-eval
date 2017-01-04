import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


@XmlRootElement
@XmlType(propOrder={"id", "username"})
public class Contributor {

    int id;
    String username;
    String deleted;

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public String getDeleted() {
        return deleted;
    }

    @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public void setUsername(String username) {
        this.username = username;
    }

    @XmlAttribute
    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
} 

