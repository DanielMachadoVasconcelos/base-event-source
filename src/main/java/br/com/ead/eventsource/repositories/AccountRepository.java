package br.com.ead.eventsource.repositories;

import br.com.ead.eventsource.mapper.AccountMapper;
import br.com.ead.eventsource.model.Account;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Log4j2
@Repository
@AllArgsConstructor
public class AccountRepository {

    private DynamoDbAsyncClient client;

    @Value("${amazon.aws.tableName}")
    private String tableName;

    public Mono<Account> openAccount(Account account) {
        CompletableFuture<PutItemResponse> item = client.putItem(PutItemRequest.builder()
                .item(AccountMapper.toMap(account))
                .tableName(tableName)
                .build());
        return Mono.fromFuture(item)
                .map(PutItemResponse::attributes)
                .map(AccountMapper::fromMap);
    }

    public Mono<Account> getAccount(String id) {
        GetItemRequest request = GetItemRequest.builder()
                .tableName(tableName)
                .consistentRead(true)
                .key(Map.of("id", AttributeValue.builder().s(id).build()))
                .build();
        CompletableFuture<GetItemResponse> item = client.getItem(request);
        return Mono.fromFuture(item)
                .map(GetItemResponse::item)
                .map(AccountMapper::fromMap)
                .doOnError(log::error);
    }


}
