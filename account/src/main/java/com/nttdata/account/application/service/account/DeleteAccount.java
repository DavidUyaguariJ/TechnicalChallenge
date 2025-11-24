package com.nttdata.account.application.service.account;

import com.nttdata.account.application.port.account.in.DeleteAccountUseCase;
import com.nttdata.account.application.port.account.out.DeleteAccountPort;
import com.nttdata.account.commons.configuration.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class DeleteAccount implements DeleteAccountUseCase {

    private final DeleteAccountPort deleteAccountPort;

    @Override
    public Mono<Void> deleteAccount(Long id) {
        return deleteAccountPort.delete(id);
    }
}
