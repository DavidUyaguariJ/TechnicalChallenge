package com.nttdata.account.application.service.movement;

import com.nttdata.account.application.port.account.out.LoadAccountPort;
import com.nttdata.account.application.port.customer.out.ValidateCustomerPort;
import com.nttdata.account.application.port.movement.in.GetReportUseCase;
import com.nttdata.account.application.port.movement.in.ReportDto;
import com.nttdata.account.application.port.movement.out.ListMovementPort;
import com.nttdata.account.commons.configuration.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@UseCase
@RequiredArgsConstructor
public class GetReportMovement implements GetReportUseCase {

    public final ListMovementPort listMovementPort;
    private final LoadAccountPort loadAccountPort;
    private final ValidateCustomerPort validateCustomerPort;

    @Override
    public Flux<ReportDto> getReport(Long clientId, Date startDate, Date endDate) {
        return listMovementPort.listMovement(clientId, startDate, endDate)
                .flatMap(movement -> loadAccountPort.findAccountById(movement.getAccountId())
                        .flatMap(account -> validateCustomerPort.getCustomer(clientId)
                                .flatMap(customer -> {
                                    ReportDto dto = new ReportDto();
                                    dto.setDate(movement.getDate());
                                    dto.setClient(customer.getName());
                                    dto.setAccountNumber(account.getNumber());
                                    dto.setType(account.getType());
                                    dto.setStatus(account.isStatus());
                                    dto.setMovementType(movement.getType());
                                    dto.setInitialBalance(account.getInitialBalance());
                                    dto.setFinalBalance(movement.getBalance());
                                    return Mono.just(dto);
                                })));
    }
}
