package br.com.ead.eventsource.controllers;

import br.com.ead.eventsource.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@ActiveProfiles(profiles = {"test"})
class AccountResourceTest {

    WebTestClient webTestClient;

    @Autowired
    AccountResource accountResource;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(accountResource).build();
    }

    @Test
    void whenGetOneAccountThenReturnStatusCodeOKAndAccountBody() {
        webTestClient.get()
                .uri("/account/{id}", "1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Account.class)
        ;
    }
}