package com.nttdata.account.application.port.account.out;

import com.nttdata.account.domain.Account;
import reactor.core.publisher.Mono;

public interface UpdateAccountPort {
    Mono<Account> update(Account account);
}
