package com.nttdata.account.application.port.movement.in;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ReportDto {
    Date date;
    String client;
    String accountNumber;
    String type;
    boolean status;
    String movementType;
    BigDecimal initialBalance;
    BigDecimal finalBalance;
}
