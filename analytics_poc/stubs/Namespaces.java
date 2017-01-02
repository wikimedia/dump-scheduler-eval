import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Namespaces", propOrder={"namespace"})
public class Namespaces {

    List<Namespace> namespace;
    
    public List<Namespace> getNamespace() {
	if (namespace == null) {
            namespace = new ArrayList<Namespace>();
        }
        return namespace;
    }

} 

