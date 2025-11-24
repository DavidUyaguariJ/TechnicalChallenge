package com.nttdata.account.adapter.movements.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<MovementEntity, Long> {
    List<MovementEntity> findByAccountIdAndDateBetween(Long accountId, Date startDate, Date endDate);
}
