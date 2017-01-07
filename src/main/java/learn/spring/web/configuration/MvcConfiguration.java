package learn.spring.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan({
        "learn.spring.core.app",
        "learn.spring.core.configuration",
        "learn.spring.core.dao",
        "learn.spring.core.entity",
        "learn.spring.core.services",
        "learn.spring.core.aspects",
        "learn.spring.web.configuration",
        "learn.spring.web.controllers"
})
public class MvcConfiguration  extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        super.configureViewResolvers(registry);
//        registry.jsp("/WEB-INF/views/", ".jsp");
//    }
    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver(){
        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
        freeMarkerViewResolver.setPrefix("");
        freeMarkerViewResolver.setSuffix(".ftl");
        freeMarkerViewResolver.setCache(true);
        freeMarkerViewResolver.setOrder(0);
        return freeMarkerViewResolver;
    }

    @Bean
    public ResourceBundleViewResolver resourceBundleViewResolver(){
        ResourceBundleViewResolver resourceBundleViewResolver = new ResourceBundleViewResolver();
        resourceBundleViewResolver.setBasename("properties/resource-views-mapping");
        resourceBundleViewResolver.setOrder(1);
        return resourceBundleViewResolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/views/ftl/");
        return freeMarkerConfigurer;
    }

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");

        return resolver;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxInMemorySize(500000);
        multipartResolver.setMaxUploadSizePerFile(100000);
        multipartResolver.setDefaultEncoding("urf-8");
        return multipartResolver;
    }
}
