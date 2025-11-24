package com.nttdata.account.adapter.account.out;


import com.nttdata.account.application.port.account.out.*;
import com.nttdata.account.commons.configuration.PersistenceAdapter;
import com.nttdata.account.domain.Account;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


@PersistenceAdapter
@RequiredArgsConstructor
public class AccountPersitenceAdapter implements CreateAccountPort, UpdateAccountPort, DeleteAccountPort, LoadAccountPort, ListAccountPort {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public Mono<Account> create(Account account) {
        return Mono.fromCallable(() -> accountMapper.toDomain(accountRepository.save(accountMapper.toEntity(account))));
    }

    @Override
    public Mono<Account> update(Account account) {
        return Mono.fromCallable(() -> accountMapper.toDomain(accountRepository.save(accountMapper.toEntity(account))));
    }

    @Override
    public Mono<Void> delete(Long id) {
        return Mono.fromRunnable(() -> accountRepository.deleteById(id));
    }

    @Override
    public Mono<Account> findAccountById(Long id) {
        return Mono.fromCallable(() ->
                accountRepository.findById(id)
                        .map(accountMapper::toDomain)
                        .orElse(null)
        );
    }

    @Override
    public Flux<Account> listAccounts() {
        return Mono.fromCallable(accountRepository::findAll
                )
                .flatMapMany(Flux::fromIterable)
                .map(accountMapper::toDomain)
                .subscribeOn(Schedulers.boundedElastic());
    }
}