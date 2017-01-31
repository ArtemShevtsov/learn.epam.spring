package learn.spring.web.clients;

import learn.spring.core.entity.User;
import learn.spring.core.entity.generated.ObjectFactory;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

/**
 * Created by artem_shevtsov on 1/31/17.
 */
@Component
public class SOAPServiceClient extends WebServiceGatewaySupport {
    public SOAPServiceClient() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(ObjectFactory.class.getPackage().getName());

        this.setDefaultUri("http://localhost:8090/ws");
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);
    }

    public User getUser(){
        User u = (User)getWebServiceTemplate().marshalSendAndReceive(new learn.spring.core.entity.generated.User());
        return u;
    }

    public static void main(String[] args) {
        SaajSoapMessageFactory messageFactory = new SaajSoapMessageFactory();
        messageFactory.afterPropertiesSet();
        WebServiceTemplate serviceTemplate = new WebServiceTemplate(messageFactory);
        serviceTemplate.setDefaultUri("http://localhost:8090/ws");

        StreamResult result = new StreamResult(System.out);

        System.out.println("\n\ngetFirstUser:");
        StreamSource source = new StreamSource(new StringReader("<getFirstUser xmlns=\"http://localhost:8090/\"></getFirstUser>"));
        serviceTemplate.sendSourceAndReceiveToResult(source, result);

        System.out.println("\n\ngetUserByEmail \"orest@mail.com\":");
        StreamSource source1 = new StreamSource(new StringReader("<getUserByEmail xmlns=\"http://localhost:8090/\"></getUserByEmail>"));
        serviceTemplate.sendSourceAndReceiveToResult(source1, result);
    }
}
