package com.nttdata.account.application.port.account.out;

import com.nttdata.account.domain.Account;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ListAccountPort {
    Flux<Account> listAccounts();
}
