package com.nttdata.account.adapter.movements.out;

import com.nttdata.account.domain.Movement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovementMapper {

    MovementEntity toEntity(Movement account);

    Movement toDomain(MovementEntity entity);
}
