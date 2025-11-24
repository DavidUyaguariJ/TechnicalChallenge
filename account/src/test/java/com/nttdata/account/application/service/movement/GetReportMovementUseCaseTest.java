package com.nttdata.account.application.service.movement;

import com.nttdata.account.application.port.customer.out.Customer;
import com.nttdata.account.application.port.movement.in.ReportDto;
import com.nttdata.account.domain.Account;
import com.nttdata.account.domain.Movement;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetReportMovementUseCaseTest {

    @Test
    void testGenerateReport() {
        List<Movement> movements = new ArrayList<>();

        Movement movement1 = new Movement();
        movement1.setAccountId(1L);
        movement1.setDate(new Date());
        movement1.setType("CREDIT");
        movement1.setValue(new BigDecimal("200"));
        movement1.setBalance(new BigDecimal("700"));
        movements.add(movement1);

        Account account = new Account();
        account.setId(1L);
        account.setNumber("ACC-1001");
        account.setInitialBalance(new BigDecimal("500"));
        account.setType("DEBIT");
        account.setStatus(true);

        Customer customer = new Customer();
        customer.setName("David Uyaguari");

        List<ReportDto> reportList = new ArrayList<>();
        for (Movement m : movements) {
            ReportDto dto = new ReportDto();
            dto.setDate(m.getDate());
            dto.setClient(customer.getName());
            dto.setAccountNumber(account.getNumber());
            dto.setType(account.getType());
            dto.setStatus(account.isStatus());
            dto.setMovementType(m.getType());
            dto.setInitialBalance(account.getInitialBalance());
            dto.setFinalBalance(m.getBalance());
            reportList.add(dto);
        }
        assertEquals(1, reportList.size());
        assertEquals("David Uyaguari", reportList.get(0).getClient());
        assertEquals("ACC-1001", reportList.get(0).getAccountNumber());
        assertEquals(new BigDecimal("700"), reportList.get(0).getFinalBalance());
        assertEquals("CREDIT", reportList.get(0).getMovementType());
    }
}