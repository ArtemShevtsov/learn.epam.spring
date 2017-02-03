package learn.spring.web.clients;

import learn.spring.core.entity.generated.GetUserById;
import learn.spring.core.entity.generated.UserResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * Created by artem_shevtsov on 1/31/17.
 */
@Component
public class SOAPServiceClient extends WebServiceGatewaySupport {
    public SOAPServiceClient() throws JAXBException {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("learn.spring.core.entity.generated");
        this.setDefaultUri("http://localhost:8090/ws");
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);
    }

    public static void main(String[] args) throws InterruptedException, JAXBException {
        SaajSoapMessageFactory messageFactory = new SaajSoapMessageFactory();
        messageFactory.afterPropertiesSet();
        WebServiceTemplate serviceTemplate = new WebServiceTemplate(messageFactory);
        serviceTemplate.setDefaultUri("http://localhost:8090/ws");

        StreamResult result = new StreamResult(System.out);

        //Working example
        System.out.println("\n\ngetFirstUser:");
        StreamSource source = new StreamSource(new StringReader("<getFirstUser xmlns=\"http://localhost:8090/ws\"></getFirstUser>"));
        serviceTemplate.sendSourceAndReceiveToResult(source, result);

        sleep(TimeUnit.SECONDS.toMillis(2L));


        System.out.println("\n\ngetUserById \"2\":");
        SOAPServiceClient soapServiceClient = new SOAPServiceClient();
        WebServiceTemplate webServiceTemplate1 = soapServiceClient.getWebServiceTemplate();
        GetUserById userRequest = new GetUserById();
//        learn.spring.core.entity.ws.GetUserById userRequest = new learn.spring.core.entity.ws.GetUserById();
        userRequest.setUserId(2);
        Object o = webServiceTemplate1.marshalSendAndReceive(userRequest, new SoapActionCallback("http://localhost:8090/ws/userResponse"));
        System.out.println(((UserResponse)o).getUserValue());
    }
}
