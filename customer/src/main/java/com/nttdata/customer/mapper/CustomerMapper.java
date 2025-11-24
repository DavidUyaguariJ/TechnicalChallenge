package com.nttdata.customer.mapper;

import com.nttdata.customer.dto.CustomerRequest;
import com.nttdata.customer.dto.CustomerResponse;
import com.nttdata.customer.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity(CustomerRequest request);

    CustomerResponse toResponse(Customer entity);

    void updateEntity(@MappingTarget Customer entity, CustomerRequest request);

}
