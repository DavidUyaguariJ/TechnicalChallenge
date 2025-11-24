package com.nttdata.account.adapter.account.in;

import com.nttdata.account.application.port.account.in.*;
import com.nttdata.account.commons.configuration.WebAdapter;
import com.nttdata.account.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebAdapter
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final CreateAccountUserCase createAccountUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;
    private final LoadAccountUseCase loadAccountUseCase;
    private final ListAccountUseCase listAccountUseCase;

    @PostMapping
    public Mono<Account> create(@RequestBody Account account) {
        return createAccountUseCase.createAccount(account);
    }

    @PutMapping
    public Mono<Account> update(@RequestBody Account account) {
        return updateAccountUseCase.updateAccount(account);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return deleteAccountUseCase.deleteAccount(id);
    }

    @GetMapping("/{id}")
    public Mono<Account> findById(@PathVariable Long id) {
        return loadAccountUseCase.loadAccount(id);
    }

    @GetMapping
    public Flux<Account> listAll() {
        return listAccountUseCase.listAccounts();
    }
}
