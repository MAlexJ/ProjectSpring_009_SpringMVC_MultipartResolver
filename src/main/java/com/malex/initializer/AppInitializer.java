package com.malex.initializer;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class AppInitializer implements WebApplicationInitializer {

    private final String MAPPING_URL = "/";
    private final String CONFIG_LOCATION = "com.malex.configuration";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(context));

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping(MAPPING_URL);
        dispatcher.setMultipartConfig(getMultiPartConfigElement()); //TODO Step 1
    }

    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(CONFIG_LOCATION);
        return context;
    }

    // https://github.com/ralscha/extdirectspring/wiki/Config-Spring-Java-Config
    private MultipartConfigElement getMultiPartConfigElement() {                  //TODO Step 2
        return new MultipartConfigElement("", 2000000, 10000000, 0);
    }

}
