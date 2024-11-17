package ru.andreyszdlv.springbootstarters3loadimage.epp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import ru.andreyszdlv.springbootstarters3loadimage.util.StringUtils;

public class EndpointEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String providerName = environment.getProperty("s3-load-image-starter.provider");

        if(providerName.equals("minio")) {
            String endpoint = environment.getProperty("s3-load-image-starter.endpoint");
            if(StringUtils.isNullOrEmpty(endpoint)){
                throw new IllegalArgumentException("Endpoint must be set for minio connection");
            }
        }
    }
}
