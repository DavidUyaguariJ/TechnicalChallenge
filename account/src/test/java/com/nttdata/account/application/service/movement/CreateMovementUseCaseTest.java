package com.nttdata.account.application.service.movement;

import com.nttdata.account.domain.Account;
import com.nttdata.account.domain.Movement;
import com.nttdata.account.enumerables.MovementType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CreateMovementUseCaseTest {

    @Test
    void testCreateMovement() {
        Account account = new Account();
        account.setId(1L);
        account.setInitialBalance(new BigDecimal("500"));
        Movement movement = new Movement();
        movement.setAccountId(1L);
        movement.setType("CREDIT");
        movement.setValue(new BigDecimal("100"));
        movement.applyMovement(account.getInitialBalance(), movement.getValue(), MovementType.CREDIT);
        assertEquals(new BigDecimal("600"), movement.getBalance());
    }

    @Test
    void testCreateDebitMovement() {
        Account account = new Account();
        account.setId(1L);
        account.setInitialBalance(new BigDecimal("500"));

        Movement movement = new Movement();
        movement.setAccountId(1L);
        movement.setType("DEBIT");
        movement.setValue(new BigDecimal("150"));

        movement.applyMovement(account.getInitialBalance(), movement.getValue(), MovementType.DEBIT);

        assertEquals(new BigDecimal("350"), movement.getBalance());
    }

    @Test
    void testMovementValueValidation() {
        Movement movement = new Movement();
        movement.setValue(new BigDecimal("-10"));
        assertFalse(movement.isMovementValueValid(movement.getValue()));
    }
}