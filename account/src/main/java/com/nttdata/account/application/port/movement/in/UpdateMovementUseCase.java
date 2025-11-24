package com.nttdata.account.application.port.movement.in;

import com.nttdata.account.domain.Movement;
import reactor.core.publisher.Mono;

public interface UpdateMovementUseCase {
    Mono<Movement> updateMovement(Movement movement);
}
