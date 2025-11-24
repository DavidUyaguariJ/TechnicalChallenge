package com.nttdata.account.application.service.account;

import com.nttdata.account.application.port.account.in.ListAccountUseCase;
import com.nttdata.account.application.port.account.out.ListAccountPort;
import com.nttdata.account.commons.configuration.UseCase;
import com.nttdata.account.domain.Account;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@UseCase
@RequiredArgsConstructor
public class ListAccount implements ListAccountUseCase {

    private final ListAccountPort listAccountPort;

    @Override
    public Flux<Account> listAccounts() {
        return listAccountPort.listAccounts();
    }
}
