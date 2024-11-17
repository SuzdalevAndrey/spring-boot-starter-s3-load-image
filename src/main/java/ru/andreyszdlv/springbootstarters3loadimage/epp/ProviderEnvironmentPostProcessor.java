package ru.andreyszdlv.springbootstarters3loadimage.epp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import ru.andreyszdlv.springbootstarters3loadimage.util.StringUtils;

public class ProviderEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment,
                                       SpringApplication application) {
        String providerName = environment.getProperty("s3-load-image-starter.provider");

        if(StringUtils.isNullOrEmpty(providerName)) {
            throw new IllegalStateException("Property 's3-load-image-starter.provider' must be set!");
        }

        if (!"aws".equals(providerName) && !"minio".equals(providerName)) {
            throw new IllegalStateException("Invalid value for 's3-load-image-starter.provider'. Must be either 'aws' or 'minio'.");
        }
    }
}
