import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Comment {

    String comment;
    String deleted;

    public String getComment() {
        return comment;
    }

    public String getDeleted() {
        return deleted;
    }

    @XmlElement
    public void setComment(String comment) {
        this.comment = comment;
    }

    @XmlAttribute
    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

}
