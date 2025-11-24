package com.nttdata.account.application.port.movement.in;

import reactor.core.publisher.Mono;

public interface DeleteMovementUseCase {
    Mono<Void> deleteMovement(Long id);
}
