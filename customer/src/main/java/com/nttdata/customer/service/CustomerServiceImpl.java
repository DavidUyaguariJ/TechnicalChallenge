package com.nttdata.customer.service;

import com.nttdata.customer.dto.CustomerRequest;
import com.nttdata.customer.dto.CustomerResponse;
import com.nttdata.customer.exception.DomainException;
import com.nttdata.customer.mapper.CustomerMapper;
import com.nttdata.customer.model.Customer;
import com.nttdata.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    @Override
    public Mono<CustomerResponse> createCustomer(CustomerRequest request) {
        return Mono.fromCallable(() -> {
                    Customer saved = customerRepository.save(customerMapper.toEntity(request));
                    log.info("Customer created Identification: {}", saved.getIdentification());
                    return customerMapper.toResponse(saved);
                })
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<CustomerResponse> getCustomerById(Long id) {
        return Mono.fromCallable(() -> {
                    Customer customer = findCustomerById(id);
                    return customerMapper.toResponse(customer);
                })
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<CustomerResponse> getAllCustomers() {
        return Flux.defer(() -> {
                    List<Customer> list = customerRepository.findAll();
                    log.info("Customer list retrieved");
                    return Flux.fromIterable(list);
                })
                .map(customerMapper::toResponse)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<CustomerResponse> updateCustomer(CustomerRequest request) {
        if (request.id() == null) {
            throw new DomainException("Customer request id can not be null");
        }
        return Mono.fromCallable(() -> {
                    Customer oldCustomer = findCustomerById(request.id());
                    customerMapper.updateEntity(oldCustomer, request);
                    Customer updated = customerRepository.save(oldCustomer);
                    log.info("Customer updated with id {}", updated.getId());
                    return customerMapper.toResponse(updated);
                })
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Void> deleteCustomer(Long id) {
        return Mono.fromRunnable(() -> {
                    findCustomerById(id);
                    customerRepository.deleteById(id);
                    log.info("Customer deleted with id {}", id);
                })
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }

    private Customer findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new DomainException("Customer not found"));
    }

}
