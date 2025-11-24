package com.nttdata.account.application.service.movement;

import com.nttdata.account.application.port.account.out.LoadAccountPort;
import com.nttdata.account.application.port.movement.in.UpdateMovementUseCase;
import com.nttdata.account.application.port.movement.out.UpdateMovementPort;
import com.nttdata.account.commons.configuration.UseCase;
import com.nttdata.account.domain.Movement;
import com.nttdata.account.enumerables.MovementType;
import com.nttdata.account.exception.DomainException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class updateMovement implements UpdateMovementUseCase {

    private final UpdateMovementPort updateMovementPort;
    private final LoadAccountPort loadAccountPort;

    @Override
    public Mono<Movement> updateMovement(Movement movement) {
        return loadAccountPort.findAccountById(movement.getAccountId())
                .switchIfEmpty(Mono.error(new DomainException("Account doesn't exist")))
                .flatMap(account -> {
                    if (!movement.isMovementValueValid(movement.getValue())) {
                        log.error("Invalid movement value: {}", movement.getValue());
                        return Mono.error(new DomainException("The value of the movement must be greater than zero."));
                    }
                    if (!account.isBalanceGreaterThan(movement.getValue())) {
                        log.error("Movement value {} is greater than account balance {}", movement.getValue(), account.getInitialBalance());
                        return Mono.error(new DomainException("The value of the movement must be greater than initial balance."));
                    }
                    MovementType movementType;
                    movementType = MovementType.valueOf(movement.getType());
                    movement.setAccountId(account.getId());
                    movement.applyMovement(account.getInitialBalance(), movement.getValue(), movementType);
                    movement.setDate(movement.getDate());
                    return updateMovementPort.update(movement).doOnSuccess(m -> log.info("Movement successfully created: {}", m.getId()))
                            .doOnError(e -> log.error("Error creating movement", e));
                });
    }
}
