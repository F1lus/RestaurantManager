package org.restaurantmanager.backend.config.integration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AngularConfig implements WebMvcConfigurer {

    private static final String URL_PATH = "/";
    private static final String VIEW_NAME = "forward:/browser/index.html";
    private static final String RESOURCE_HANDLER_PATH_PATTERN = "/**";
    private static final String RESOURCE_LOCATION = "classpath:/static/browser/";
    private static final int CACHE_PERIOD = 0;
    private static final boolean RESOURCE_CHAIN = true;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(URL_PATH).setViewName(VIEW_NAME);
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(RESOURCE_HANDLER_PATH_PATTERN)
                .addResourceLocations(RESOURCE_LOCATION)
                .setCachePeriod(CACHE_PERIOD)
                .resourceChain(RESOURCE_CHAIN)
                .addResolver(new AngularPathResourceResolver());
    }

}
