import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Namespaces", propOrder = {"namespace"})
public class Namespaces {

    List<Namespace> namespace;
    
    public List<Namespace> getNamespace() {
        if (namespace == null) {
            namespace = new ArrayList<Namespace>();
        }
        return namespace;
    }

} 

