package ru.andreyszdlv.springbootstarters3loadimage.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.andreyszdlv.springbootstarters3loadimage.props.S3Properties;
import ru.andreyszdlv.springbootstarters3loadimage.service.S3Service;
import ru.andreyszdlv.springbootstarters3loadimage.service.impl.ImageServiceImpl;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.net.URI;

@Configuration
@EnableConfigurationProperties(S3Properties.class)
public class S3AutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "s3-load-image-starter.provider", havingValue = "minio")
    public S3Client minioClient(S3Properties s3Properties) {
        return S3Client
                .builder()
                .endpointOverride(URI.create(s3Properties.getEndpoint()))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                            AwsBasicCredentials.create(
                                    s3Properties.getAccessKey(),
                                    s3Properties.getSecretKey()
                            )
                        )
                )
                .region(Region.of(s3Properties.getRegion()))
                .serviceConfiguration(
                        S3Configuration
                                .builder()
                                .pathStyleAccessEnabled(true)
                                .build()
                )
                .build();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "s3-load-image-starter.provider", havingValue = "minio")
    public S3Presigner minioPresigner(S3Properties s3Properties){
        return S3Presigner
                .builder()
                .endpointOverride(URI.create(s3Properties.getEndpoint()))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                            AwsBasicCredentials.create(
                                    s3Properties.getAccessKey(),
                                    s3Properties.getSecretKey()
                            )
                        )
                )
                .region(Region.of(s3Properties.getRegion()))
                .serviceConfiguration(
                        S3Configuration
                                .builder()
                                .pathStyleAccessEnabled(true)
                                .build()
                )
                .build();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "s3-load-image-starter.provider", havingValue = "aws")
    public S3Client awsClient(S3Properties s3Properties) {
        return S3Client
                .builder()
                .region(Region.of(s3Properties.getRegion()))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(
                                        s3Properties.getAccessKey(),
                                        s3Properties.getSecretKey()
                                )
                        )
                )
                .build();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "s3-load-image-starter.provider", havingValue = "aws")
    public S3Presigner awsPresigner(S3Properties s3Properties){
        return S3Presigner
                .builder()
                .region(Region.of(s3Properties.getRegion()))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(
                                        s3Properties.getAccessKey(),
                                        s3Properties.getSecretKey()
                                )
                        )
                )
                .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public S3Service s3Service(S3Client s3Client,
                               S3Presigner s3Presigner,
                               S3Properties s3Properties) {
        return new S3Service(s3Client, s3Properties, s3Presigner);
    }

    @Bean
    @ConditionalOnMissingBean
    public ImageServiceImpl imageService(S3Service s3Service){
        return new ImageServiceImpl(s3Service);
    }

    @Bean
    @ConditionalOnMissingBean
    public S3Initializer s3Initializer(S3Properties s3Properties, S3Client s3Client){
        return new S3Initializer(s3Properties, s3Client);
    }
}
