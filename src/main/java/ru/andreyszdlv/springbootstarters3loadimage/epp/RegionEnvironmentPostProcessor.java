package ru.andreyszdlv.springbootstarters3loadimage.epp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import ru.andreyszdlv.springbootstarters3loadimage.util.StringUtils;

public class RegionEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String providerName = environment.getProperty("s3-load-image-starter.provider");

        if(providerName.equals("aws")) {
            String region = environment.getProperty("s3-load-image-starter.region");
            if(StringUtils.isNullOrEmpty(region)){
                throw new IllegalArgumentException("Region must be set for aws connection");
            }
        }
    }
}
