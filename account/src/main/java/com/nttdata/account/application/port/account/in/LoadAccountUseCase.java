package com.nttdata.account.application.port.account.in;

import com.nttdata.account.domain.Account;
import reactor.core.publisher.Mono;

public interface LoadAccountUseCase {
    Mono<Account> loadAccount(Long id);

}
