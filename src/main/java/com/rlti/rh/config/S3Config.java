package com.rlti.rh.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Value("${aws.s3.accessKey}")
    private String accessKey;

    @Value("${aws.s3.secretKey}")
    private String secretKey;

    @Value("${aws.s3.region}")
    private String region;

    @Value("${aws.s3.endpoint}")
    private String endpoint;

    @Bean
    public AmazonS3 amazonS3Client() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

        AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials));

        // Se o endpoint for definido (ambiente de dev/test), use o LocalStack
        if (endpoint != null && !endpoint.isEmpty()) {
            builder.withEndpointConfiguration(
                    new AwsClientBuilder.EndpointConfiguration(endpoint, region)
            )
                    .withPathStyleAccessEnabled(true);
        } else {
            // Caso contrário, use a região (ambiente de produção)
            builder.withRegion(region);
        }

        return builder.build();
    }
}
