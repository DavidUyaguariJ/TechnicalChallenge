package com.nttdata.account.adapter.movements.in;

import com.nttdata.account.application.port.movement.in.*;
import com.nttdata.account.commons.configuration.WebAdapter;
import com.nttdata.account.domain.Movement;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@WebAdapter
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movements")
public class MovementController {

    private final CreateMovementUseCase createMovementUseCase;
    private final DeleteMovementUseCase deleteMovementUseCase;
    private final UpdateMovementUseCase updateMovementUseCase;
    private final GetReportUseCase getReportUseCase;

    @PostMapping
    public Mono<Movement> createMovement(@RequestBody Movement movement) {
        return createMovementUseCase.createMovement(movement);
    }

    @PutMapping
    public Mono<Movement> updateMovement(@RequestBody Movement movement) {
        return updateMovementUseCase.updateMovement(movement);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteMovement(@PathVariable Long id) {
        return deleteMovementUseCase.deleteMovement(id);
    }

    @GetMapping("/reports/{clientId}")
    public Flux<ReportDto> getReport(
            @PathVariable("clientId") Long clientId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate
    ) {
        return getReportUseCase.getReport(clientId, startDate, endDate);
    }
}