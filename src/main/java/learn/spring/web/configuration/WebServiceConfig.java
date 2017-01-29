package learn.spring.web.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Created by aftor on 29.01.17.
 */
@EnableWs
@Component
public class WebServiceConfig extends WsConfigurerAdapter {

//    @Bean
//    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext){
//        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
//        servlet.setApplicationContext(applicationContext);
//        servlet.setTransformSchemaLocations(true);
//        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servlet, "/ws/");
//        return servletRegistrationBean;
//    }

    @Bean(name="booking")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema){
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("BookingAppPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://example.com/booking-web-service");
        wsdl11Definition.setSchema(schema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema schema(){
        return new SimpleXsdSchema( new ClassPathResource("schema/schema1.xsd"));
    }
}
