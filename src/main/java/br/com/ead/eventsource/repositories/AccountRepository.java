package br.com.ead.eventsource.repositories;

import br.com.ead.eventsource.model.Account;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemResponse;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static software.amazon.awssdk.services.dynamodb.model.AttributeValue.*;

@Log4j2
@Repository
@AllArgsConstructor
@NoArgsConstructor
public class AccountRepository  {

    private DynamoDbAsyncClient client;

    @Value("${amazon.aws.tableName}")
    private String tableName;

    public CompletableFuture<PutItemResponse> openAccount(Account account){
        Builder builder = builder();
        return client.putItem(PutItemRequest.builder().item(
                        Map.of( "id", builder.s(account.getId()).build(),
                                "status", builder.s("CREATED").build(),
                                "createdAt", builder.s(LocalDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE)).build(),
                                "amount", builder.s(account.getAmount().toString()).build()))
                        .tableName(tableName)
                        .build());
    }
}
