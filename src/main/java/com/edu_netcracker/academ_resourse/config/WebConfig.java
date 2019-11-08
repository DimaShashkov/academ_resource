package com.edu_netcracker.academ_resourse.config;

import com.edu_netcracker.academ_resourse.schedule.parsing.JsoupPars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.edu_netcracker.academ_resourse")
public class WebConfig extends WebMvcConfigurerAdapter {
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/" };


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }



    @Bean
    public ViewResolver getViewResolver() {
       FreeMarkerViewResolver freeMarkerViewResolver =  new FreeMarkerViewResolver();
       freeMarkerViewResolver.setOrder(1);
       freeMarkerViewResolver.setSuffix(".html");
       freeMarkerViewResolver.setContentType("text/html; charset=UTF-8");

       return freeMarkerViewResolver;
    }





    @Bean
    JsoupPars getJsoupPars() {
        return new JsoupPars();
    }
}
