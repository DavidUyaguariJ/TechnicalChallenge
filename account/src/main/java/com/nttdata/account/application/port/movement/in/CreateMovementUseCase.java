package com.nttdata.account.application.port.movement.in;

import com.nttdata.account.domain.Movement;
import reactor.core.publisher.Mono;

public interface CreateMovementUseCase {
    Mono<Movement> createMovement(Movement account);
}
