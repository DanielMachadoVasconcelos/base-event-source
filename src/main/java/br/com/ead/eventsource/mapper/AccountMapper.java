package br.com.ead.eventsource.mapper;

import br.com.ead.eventsource.model.Account;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static software.amazon.awssdk.services.dynamodb.model.AttributeValue.builder;

public class AccountMapper {

    public static List<Account> fromList(List<Map<String, AttributeValue>> items) {
        return items.stream()
                .map(AccountMapper::fromMap)
                .collect(Collectors.toList());
    }

    public static Account fromMap(Map<String, AttributeValue> attributeValueMap) {
        Account account = new Account();
        account.setId(attributeValueMap.get("id").s());
        account.setAmount(Long.valueOf(attributeValueMap.get("amount").s()));
        account.setCreatedAt(OffsetDateTime.parse(attributeValueMap.get("createdAt").s(),ISO_LOCAL_DATE));
        account.setStatus(attributeValueMap.get("status").s());
        return account;
    }

    public static Map<String, AttributeValue> toMap(Account account) {
        AttributeValue.Builder builder = builder();
        return  Map.of( "id", builder.s(account.getId()).build(),
                "status", builder.s("CREATED").build(),
                "createdAt", builder.s(OffsetDateTime.now(ZoneOffset.UTC).format(ISO_LOCAL_DATE)).build(),
                "amount", builder.s(account.getAmount().toString()).build());

    }
}
