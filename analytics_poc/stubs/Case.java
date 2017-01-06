import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Case")
@XmlEnum
public enum Case {

    @XmlEnumValue("first-letter")
    FIRST_LETTER("first-letter"),
    @XmlEnumValue("case-sensitive")
    CASE_SENSITIVE("case-sensitive"),
    @XmlEnumValue("case-insensitive")
    CASE_INSENSITIVE("case-insensitive");
    private final String value;

    Case(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Case fromValue(String v) {
        for (Case c: Case.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
