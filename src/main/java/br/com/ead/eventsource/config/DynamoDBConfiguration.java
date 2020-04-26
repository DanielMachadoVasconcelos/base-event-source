package br.com.ead.eventsource.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import static com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import static com.amazonaws.regions.Regions.US_WEST_2;

@Configuration
@EnableDynamoDBRepositories(basePackages = "br.com.ead.eventsource.repositories")
public class DynamoDBConfiguration {

    @Value("${amazon.aws.awsAccessKey}")
    private String awsAccessKey;

    @Value("${amazon.aws.accesskey}")
    private String awsSecretKey;

    @Value("${amazon.dynamodb.endpoint}")
    private String dynamoDbEndpoint;

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
                .withRegion(US_WEST_2);

        if (!StringUtils.isEmpty(dynamoDbEndpoint)) {
            builder.setEndpointConfiguration(new EndpointConfiguration(dynamoDbEndpoint, US_WEST_2.getName()));
        }

        return new DynamoDBMapper(builder.build(), DynamoDBMapperConfig.DEFAULT);
    }

}
