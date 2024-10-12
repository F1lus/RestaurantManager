package org.restaurantmanager.backend.config.integration;

import lombok.NonNull;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

class AngularPathResourceResolver extends PathResourceResolver {

    @Override
    protected Resource getResource(@NonNull String resourcePath, @NonNull Resource location) throws IOException {
        Resource requestedResource = location.createRelative(resourcePath);
        return requestedResource.exists() && requestedResource.isReadable()
                ? requestedResource
                : new ClassPathResource("/static/browser/index.html");
    }

}
