package com.nttdata.account.application.port.account.in;

import reactor.core.publisher.Mono;

public interface DeleteAccountUseCase {
    Mono<Void> deleteAccount(Long id);
}
