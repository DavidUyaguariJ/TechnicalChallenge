package com.nttdata.account.application.port.movement.out;

import com.nttdata.account.domain.Movement;
import reactor.core.publisher.Mono;

public interface CreateMovementPort {
    Mono<Movement> create(Movement movement);
}
