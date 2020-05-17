package br.com.ead.eventsource.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

import java.net.URI;

import static software.amazon.awssdk.regions.Region.*;

@Configuration
public class DynamoDBConfiguration {

    @Value("${amazon.aws.accesskey}")
    private String awsAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String awsSecretKey;

    @Value("${amazon.dynamodb.endpoint}")
    private String dynamoDbEndpoint;


    @Bean
    public DynamoDbAsyncClient dynamoDbAsyncClient() {
        return DynamoDbAsyncClient.builder()
                .region(US_EAST_2)
                .endpointOverride(URI.create(dynamoDbEndpoint))
                .credentialsProvider(DefaultCredentialsProvider
                        .builder()
                        .build())
                .build();
    }
}
