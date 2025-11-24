package com.nttdata.account.application.port.customer.out;

import reactor.core.publisher.Mono;

public interface ValidateCustomerPort {
    Mono<Customer> getCustomer(Long customerId);
}
