package br.com.ead.eventsource.controllers;

import br.com.ead.eventsource.model.Account;
import br.com.ead.eventsource.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/account")
public class AccountResource {

    private final AccountService accountService;

    @PostMapping("/")
    public Mono<Account> openAccount(@RequestBody Account account){
        return accountService.openAccount(account);
    }

    @GetMapping("/{id}")
    public Mono<Account> openAccount(@PathVariable String id){
        return accountService.getAccount(id);
    }
}
