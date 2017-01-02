import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


@XmlRootElement
@XmlType(propOrder={"key", "case"})
public class Namespace {

    String value;
    Integer key;
    Case _case;
    
    public String getValue() {
        return value;
    }

    public Integer getKey() {
        return key;
    }

    public Case getCase() {
        return _case;
    }

    @XmlValue
    public void setValue(String value) {
        this.value = value;
    }

    @XmlAttribute
    public void setKey(Integer key) {
        this.key = key;
    }

    @XmlAttribute(name="case")
    public void setCase(Case _case) {
        this._case = _case;
    }
} 

