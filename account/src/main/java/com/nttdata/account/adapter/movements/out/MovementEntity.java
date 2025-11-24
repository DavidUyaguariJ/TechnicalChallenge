package com.nttdata.account.adapter.movements.out;

import com.nttdata.account.enumerables.MovementType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "movement")
public class MovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "date", nullable = false)
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private MovementType type;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

}