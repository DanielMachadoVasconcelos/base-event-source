package br.com.ead.eventsource.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private String id;

    private String status;

    private OffsetDateTime createdAt;

    private Long amount;

}
