package ru.andreyszdlv.springbootstarters3loadimage.props;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties("s3-load-image-starter")
public class S3Properties {

    private String provider;

    private String endpoint;

    private String accessKey;

    private String secretKey;

    private String bucketName;

    private int expirationUrlInMinutes;

    private String region;
}