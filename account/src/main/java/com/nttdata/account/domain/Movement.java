package com.nttdata.account.domain;

import com.nttdata.account.enumerables.MovementType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Movement {
    private Long id;
    private Long accountId;
    private Date date;
    private String type;
    private BigDecimal value;
    private BigDecimal balance;

    public boolean isMovementValueValid(BigDecimal value) {
        return value.compareTo(BigDecimal.ZERO) > 0;
    }

    public void applyMovement(BigDecimal oldValue, BigDecimal value, MovementType type) {
        switch (type) {
            case CREDIT:
                this.balance = oldValue.add(value);
                break;
            case DEBIT:
                this.balance = oldValue.subtract(value);
                break;
        }
    }
}
