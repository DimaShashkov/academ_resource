package com.edu_netcracker.academ_resourse.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc
@ComponentScan(basePackages = "com.edu_netcracker.academ_resourse")
public class MvcConfig implements WebMvcConfigurer {
//    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
//            "classpath:/META-INF/resources/", "classpath:/resources/",
//            "classpath:/static/", "classpath:/public/" };
//
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
//    }
//
//
//
//    @Bean
//    public ViewResolver getViewResolver() {
//       FreeMarkerViewResolver freeMarkerViewResolver =  new FreeMarkerViewResolver();
//       freeMarkerViewResolver.setOrder(1);
//       freeMarkerViewResolver.setSuffix(".html");
//       freeMarkerViewResolver.setContentType("text/html; charset=UTF-8");
//
//       return freeMarkerViewResolver;
//    }
//
//
//

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}

}
