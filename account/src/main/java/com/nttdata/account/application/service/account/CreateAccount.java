package com.nttdata.account.application.service.account;

import com.nttdata.account.application.port.account.in.CreateAccountUserCase;
import com.nttdata.account.application.port.account.out.CreateAccountPort;
import com.nttdata.account.application.port.customer.out.ValidateCustomerPort;
import com.nttdata.account.commons.configuration.UseCase;
import com.nttdata.account.domain.Account;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class CreateAccount implements CreateAccountUserCase {

    private final CreateAccountPort createAccountPort;
    private final ValidateCustomerPort validateCustomerPort;

    @Override
    public Mono<Account> createAccount(Account account) {
        return validateCustomerPort.getCustomer(account.getCustomerId())
                .flatMap(customer -> createAccountPort.create(account));
    }
}
