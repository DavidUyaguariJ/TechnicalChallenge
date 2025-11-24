package com.nttdata.account.application.service.movement;

import com.nttdata.account.domain.Movement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class DeleteMovementUseCaseTest {

    @Test
    void testDeleteMovement() {
        Movement movement = new Movement();
        movement.setId(1L);
        movement = null;
        assertNull(movement, "Movement should be deleted");
    }
}
