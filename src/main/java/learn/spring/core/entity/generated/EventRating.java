//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.02.07 at 01:09:42 PM EET 
//


package learn.spring.core.entity.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for eventRating.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="eventRating">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="HIGHT"/>
 *     &lt;enumeration value="MIDDLE"/>
 *     &lt;enumeration value="LOW"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "eventRating")
@XmlEnum
public enum EventRating {

    HIGHT,
    MIDDLE,
    LOW;

    public String value() {
        return name();
    }

    public static EventRating fromValue(String v) {
        return valueOf(v);
    }

}