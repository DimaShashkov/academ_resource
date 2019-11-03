package com.edu_netcracker.academ_resourse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/*.html").addResourceLocations("/WEB-INF/");
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/registration").setViewName("registration");
        registry.addViewController("/schedule").setViewName("schedule");
        registry.addViewController("/tasks").setViewName("tasks");
        registry.addViewController("/personal_account").setViewName("personal_account");
        registry.addViewController("/main").setViewName("main");
        registry.addViewController("/calendar").setViewName("calendar");
    }
}
