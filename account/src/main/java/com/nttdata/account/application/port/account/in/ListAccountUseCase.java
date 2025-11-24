package com.nttdata.account.application.port.account.in;

import com.nttdata.account.domain.Account;
import reactor.core.publisher.Flux;

public interface ListAccountUseCase {
    Flux<Account> listAccounts();
}
