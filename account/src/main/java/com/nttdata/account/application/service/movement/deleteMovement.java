package com.nttdata.account.application.service.movement;

import com.nttdata.account.application.port.movement.in.DeleteMovementUseCase;
import com.nttdata.account.application.port.movement.out.DeleteMovementPort;
import com.nttdata.account.commons.configuration.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class deleteMovement implements DeleteMovementUseCase {

    private final DeleteMovementPort deleteMovementPort;

    @Override
    public Mono<Void> deleteMovement(Long id) {
        return deleteMovementPort.delete(id);
    }

}
