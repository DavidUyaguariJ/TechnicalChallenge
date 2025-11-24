package com.nttdata.account.application.port.movement.in;

import reactor.core.publisher.Flux;

import java.util.Date;

public interface GetReportUseCase {
    Flux<ReportDto> getReport(Long id, Date startDate, Date endDate);
}
