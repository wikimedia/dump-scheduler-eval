import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
@XmlType(propOrder = {"sitename", "dbname", "base", "generator", "case", "namespaces"})
public class Siteinfo {

    String sitename;
    String dbname;
    String base;
    String generator;
    Case _case;
    Namespaces namespaces;
    
    public String getSitename() {
        return sitename;
    }

    public String getDbname() {
        return dbname;
    }

    public String getBase() {
        return base;
    }

    public String getGenerator() {
        return generator;
    }

    public Case getCase() {
        return _case;
    }

    public Namespaces getNamespaces() {
        return namespaces;
    }

    @XmlElement
    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    @XmlElement
    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    @XmlElement
    public void setBase(String base) {
        this.base = base;
    }

    @XmlElement
    public void setGenerator(String generator) {
        this.generator = generator;
    }

    @XmlElement(name = "case")
    public void setCase(Case _case) {
        this._case = _case;
    }

    @XmlElement
    public void setNamespaces(Namespaces namespaces) {
        this.namespaces = namespaces;
    }
} 

