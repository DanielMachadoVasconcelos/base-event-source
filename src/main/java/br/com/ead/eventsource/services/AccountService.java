package br.com.ead.eventsource.services;

import br.com.ead.eventsource.model.Account;
import br.com.ead.eventsource.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Log4j2
@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Mono<Account> openAccount(Account account){
        return accountRepository.openAccount(account)
                .doOnError(this::logErrorMessage);
    }

    public Mono<Account> getAccount(String id){
        return accountRepository.getAccount(id)
                .doOnError(this::logErrorMessage);
    }


    private void logErrorMessage(Throwable throwable) {
        log.error("An error when open a new account.",throwable);
    }
}
