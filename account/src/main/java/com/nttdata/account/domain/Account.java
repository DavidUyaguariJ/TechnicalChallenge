package com.nttdata.account.domain;

import com.nttdata.account.enumerables.MovementType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Account {
    private Long id;
    private String number;
    private Long customerId;
    private String type;
    private BigDecimal initialBalance;
    private boolean status;

    public Boolean isBalanceGreaterThan(BigDecimal balance) {
        return balance.compareTo(initialBalance) > 0;
    }

}
