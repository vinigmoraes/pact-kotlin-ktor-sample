package br.com.pact.accountservice.account.application

import br.com.pact.accountservice.account.core.AccountService
import br.com.pact.accountservice.account.core.ports.AccountRepository
import br.com.pact.accountservice.account.infrastructure.AccountRepositoryAdapter
import org.koin.dsl.module

val accountModule = module {
    single { AccountController(get(), get()) }
    single { AccountService(get()) }
    single<AccountRepository> { AccountRepositoryAdapter() }
}
