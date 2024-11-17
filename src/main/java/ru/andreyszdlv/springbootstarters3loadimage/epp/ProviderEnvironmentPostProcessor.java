package ru.andreyszdlv.springbootstarters3loadimage.epp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

public class ProviderEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String providerName = environment.getProperty("s3-load-image-starter.provider");

        if(providerName == null || providerName.isEmpty()) {
            throw new IllegalStateException("Property 's3-load-image-starter.provider' must be set!");
        }
    }
}
