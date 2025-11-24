package com.nttdata.account.application.port.account.out;

import com.nttdata.account.domain.Account;
import reactor.core.publisher.Mono;

public interface CreateAccountPort {
    Mono<Account> create(Account account);
}
