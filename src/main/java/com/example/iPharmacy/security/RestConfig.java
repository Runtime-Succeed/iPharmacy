package com.example.iPharmacy.security;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;

@Component
public class RestConfig extends ResourceConfig {
    public RestConfig() {
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
        register(JerseyResource.class);
    }
}