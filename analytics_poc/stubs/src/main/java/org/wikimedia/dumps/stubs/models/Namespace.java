package org.wikimedia.dumps.stubs.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement
@XmlType(propOrder = {"key", "case"})
public class Namespace {

    String value;
    Integer key;
    CaseSetting _case;
    
    public String getValue() {
        return value;
    }

    public Integer getKey() {
        return key;
    }

    public CaseSetting getCase() {
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

    @XmlAttribute(name = "case")
    public void setCase(CaseSetting _case) {
        this._case = _case;
    }
} 

