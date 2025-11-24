package com.nttdata.account.application.service.account;

import com.nttdata.account.application.port.account.in.LoadAccountUseCase;
import com.nttdata.account.application.port.account.out.LoadAccountPort;
import com.nttdata.account.commons.configuration.UseCase;
import com.nttdata.account.domain.Account;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class LoadAccount implements LoadAccountUseCase {
    private final LoadAccountPort loadAccountPort;

    @Override
    public Mono<Account> loadAccount(Long id) {
        return loadAccountPort.findAccountById(id);
    }
}
