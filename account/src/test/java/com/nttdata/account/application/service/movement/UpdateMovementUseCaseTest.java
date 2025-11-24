package com.nttdata.account.application.service.movement;

import com.nttdata.account.domain.Movement;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class UpdateMovementUseCaseTest {

    @Test
    void testUpdateMovement() {
        Movement movement = new Movement();
        movement.setId(1L);
        movement.setValue(new BigDecimal("150"));
        movement.setBalance(new BigDecimal("150"));

        movement.setValue(new BigDecimal("200"));
        movement.setBalance(movement.getValue());

        assertEquals(new BigDecimal("200"), movement.getBalance());
    }

    @Test
    void testUpdateMovementInvalidValue() {
        Movement movement = new Movement();
        movement.setValue(new BigDecimal("-50"));
        assertFalse(movement.isMovementValueValid(movement.getValue()));
    }
}
