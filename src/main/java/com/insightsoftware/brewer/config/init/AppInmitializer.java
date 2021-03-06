package com.insightsoftware.brewer.config.init;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.insightsoftware.brewer.config.JPAConfig;
import com.insightsoftware.brewer.config.SecurityConfig;
import com.insightsoftware.brewer.config.ServiceConfig;
import com.insightsoftware.brewer.config.WebConfig;

public class AppInmitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[] {JPAConfig.class, ServiceConfig.class, SecurityConfig.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[] {WebConfig.class};
  }

  @Override
  protected String[] getServletMappings() {

    return new String[] {"/"};
  }

  @Override
  protected Filter[] getServletFilters() {

    return new Filter[] {};
  }

  @Override
  protected void customizeRegistration(Dynamic registration) {
    registration.setMultipartConfig(new MultipartConfigElement(""));
  }

}
