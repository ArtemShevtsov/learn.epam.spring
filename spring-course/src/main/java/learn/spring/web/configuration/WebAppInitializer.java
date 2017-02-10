package learn.spring.web.configuration;

import learn.spring.core.app.Application;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
//        Creates the root Spring App Context
        AnnotationConfigWebApplicationContext webAppContext = new AnnotationConfigWebApplicationContext();
        webAppContext.register(MvcConfiguration.class);
        servletContext.addListener(new ContextLoaderListener(webAppContext));

//        Creates the dispatcher Servlet
        ServletRegistration.Dynamic dispatcher = servletContext
                .addServlet("dispatcher", new DispatcherServlet(webAppContext));
        dispatcher.addMapping("/");
        dispatcher.setLoadOnStartup(1);



        AnnotationConfigWebApplicationContext webAppContext1 = new AnnotationConfigWebApplicationContext();
        webAppContext1.setParent(webAppContext);
        MessageDispatcherServlet servlet = new MessageDispatcherServlet(webAppContext1);
        servlet.setTransformSchemaLocations(true);

        ServletRegistration.Dynamic WsDispatcher = servletContext.addServlet("dispatcher-ws", servlet);
        WsDispatcher.addMapping("/ws/*");
        WsDispatcher.setLoadOnStartup(1);
    }
}
