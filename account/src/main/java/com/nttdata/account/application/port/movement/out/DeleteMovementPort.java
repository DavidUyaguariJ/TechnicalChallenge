package com.nttdata.account.application.port.movement.out;

import reactor.core.publisher.Mono;

public interface DeleteMovementPort {
    Mono<Void> delete(Long id);
}
