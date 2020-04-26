package br.com.ead.eventsource.repositories;

import br.com.ead.eventsource.model.Account;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.Repository;

@EnableScan
public interface AccountRepository extends Repository<Account, String> {


}
