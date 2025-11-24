package com.nttdata.account.application.service.customer;

import com.nttdata.account.application.port.customer.out.Customer;
import com.nttdata.account.application.port.customer.out.ValidateCustomerPort;
import com.nttdata.account.commons.configuration.UseCase;
import com.nttdata.account.commons.dto.ErrorResponse;
import com.nttdata.account.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class ValidateCustomer implements ValidateCustomerPort {

    private final WebClient webClient;

    @Override
    public Mono<Customer> getCustomer(Long customerId) {
        return webClient.get()
                .uri("/api/v1/customers/{id}", customerId)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), response ->
                        response.bodyToMono(ErrorResponse.class)
                                .flatMap(errorResponse ->
                                        Mono.<Throwable>error(
                                                new DomainException(errorResponse.message()))))
                .onStatus(status -> status.is5xxServerError(), response ->
                        response.bodyToMono(ErrorResponse.class)
                                .flatMap(errorResponse ->
                                        Mono.<Throwable>error(
                                                new DomainException(errorResponse.message()))))
                .bodyToMono(Customer.class);
    }
}
