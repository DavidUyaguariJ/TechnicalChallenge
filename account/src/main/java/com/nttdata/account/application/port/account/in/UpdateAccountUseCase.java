package com.nttdata.account.application.port.account.in;

import com.nttdata.account.domain.Account;
import reactor.core.publisher.Mono;

public interface UpdateAccountUseCase {

    Mono<Account> updateAccount(Account account);
}
