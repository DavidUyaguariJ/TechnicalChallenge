package com.nttdata.customer.service;

import com.nttdata.customer.dto.CustomerRequest;
import com.nttdata.customer.dto.CustomerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    Mono<CustomerResponse> createCustomer(CustomerRequest request);

    Mono<CustomerResponse> getCustomerById(Long id);

    Flux<CustomerResponse> getAllCustomers();

    Mono<CustomerResponse> updateCustomer(CustomerRequest request);

    Mono<Void> deleteCustomer(Long id);
}
