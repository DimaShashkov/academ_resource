package com.edu_netcracker.academ_resourse.config;

import com.edu_netcracker.academ_resourse.parsing.JsoupPars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/*.html").addResourceLocations("/WEB-INF/");
    }



    @Bean
    public ViewResolver getViewResolver() {
       FreeMarkerViewResolver freeMarkerViewResolver =  new FreeMarkerViewResolver();
       freeMarkerViewResolver.setOrder(1);
       freeMarkerViewResolver.setSuffix(".html");
       freeMarkerViewResolver.setPrefix("");
       freeMarkerViewResolver.setContentType("text/html; charset=UTF-8");

       return freeMarkerViewResolver;
    }

    @Bean
    FreeMarkerConfigurer getFreemarkerConfigurer() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/views/");
        freeMarkerConfigurer.setDefaultEncoding("UTF-8");
        return freeMarkerConfigurer;
    }


    @Bean
    JsoupPars getJsoupPars() {
        return new JsoupPars();
    }
}
