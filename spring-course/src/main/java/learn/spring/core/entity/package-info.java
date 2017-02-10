/**
 * Created by artem_shevtsov on 2/3/17.
 */
@XmlSchema(
        namespace = "http://localhost:8090/ws",
        elementFormDefault = XmlNsForm.QUALIFIED,
        xmlns = {
                @XmlNs(prefix="ns1", namespaceURI="http://localhost:8090/ws")
        }
)
package learn.spring.core.entity;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;