package com.nttdata.account.adapter.account.out;

import com.nttdata.account.domain.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountEntity toEntity(Account account);

    Account toDomain(AccountEntity entity);
}
