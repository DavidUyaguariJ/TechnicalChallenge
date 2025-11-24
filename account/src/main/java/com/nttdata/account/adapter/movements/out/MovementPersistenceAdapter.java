package com.nttdata.account.adapter.movements.out;

import com.nttdata.account.application.port.movement.out.CreateMovementPort;
import com.nttdata.account.application.port.movement.out.DeleteMovementPort;
import com.nttdata.account.application.port.movement.out.ListMovementPort;
import com.nttdata.account.application.port.movement.out.UpdateMovementPort;
import com.nttdata.account.commons.configuration.PersistenceAdapter;
import com.nttdata.account.domain.Movement;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@PersistenceAdapter
@RequiredArgsConstructor
public class MovementPersistenceAdapter implements CreateMovementPort, DeleteMovementPort, UpdateMovementPort, ListMovementPort {

    private final MovementRepository movementRepository;
    private final MovementMapper movementMapper;

    @Override
    public Mono<Movement> create(Movement movement) {
        return Mono.fromCallable(() -> {
            MovementEntity entity = movementMapper.toEntity(movement);
            return movementMapper.toDomain(movementRepository.save(entity));
        });
    }

    @Override
    public Mono<Void> delete(Long id) {
        return Mono.fromRunnable(() -> movementRepository.deleteById(id));
    }

    @Override
    public Mono<Movement> update(Movement movement) {
        return Mono.fromCallable(() -> {
            MovementEntity updated = movementRepository.save(movementMapper.toEntity(movement));
            return movementMapper.toDomain(updated);
        });
    }

    @Override
    public Flux<Movement> listMovement(Long accountId, Date startDate, Date endDate) {
        return Flux.fromIterable(
                movementRepository
                        .findByAccountIdAndDateBetween(accountId, startDate, endDate)
                        .stream()
                        .map(movementMapper::toDomain)
                        .toList()
        );
    }
}
