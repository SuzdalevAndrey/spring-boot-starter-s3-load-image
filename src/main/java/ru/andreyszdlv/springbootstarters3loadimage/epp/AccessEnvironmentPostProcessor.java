package ru.andreyszdlv.springbootstarters3loadimage.epp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import ru.andreyszdlv.springbootstarters3loadimage.util.StringUtils;

public class AccessEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment,
                                       SpringApplication application) {
        String accessKey = environment.getProperty("s3-load-image-starter.access-key");
        String secretKey = environment.getProperty("s3-load-image-starter.secret-key");

        if(StringUtils.isNullOrEmpty(accessKey) || StringUtils.isNullOrEmpty(secretKey)) {
            throw new IllegalArgumentException("Access key and secret key must be set for S3 connection");
        }
    }
}
