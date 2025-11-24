package com.nttdata.account.application.port.movement.out;

import com.nttdata.account.domain.Movement;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface ListMovementPort {
    Flux<Movement> listMovement(Long id, Date startDate, Date endDate);
}
