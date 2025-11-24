package com.nttdata.account.application.port.account.in;

import com.nttdata.account.domain.Account;
import reactor.core.publisher.Mono;

public interface CreateAccountUserCase {
    Mono<Account> createAccount(Account account);
}
