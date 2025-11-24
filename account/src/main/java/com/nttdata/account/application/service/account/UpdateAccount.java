package com.nttdata.account.application.service.account;

import com.nttdata.account.application.port.account.in.UpdateAccountUseCase;
import com.nttdata.account.application.port.account.out.UpdateAccountPort;
import com.nttdata.account.application.port.customer.out.ValidateCustomerPort;
import com.nttdata.account.commons.configuration.UseCase;
import com.nttdata.account.domain.Account;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class UpdateAccount implements UpdateAccountUseCase {

    private final UpdateAccountPort updateAccountPort;
    private final ValidateCustomerPort validateCustomerPort;

    @Override
    public Mono<Account> updateAccount(Account account) {
        return validateCustomerPort.getCustomer(account.getCustomerId())
                .flatMap(customer -> updateAccountPort.update(account));
    }
}
