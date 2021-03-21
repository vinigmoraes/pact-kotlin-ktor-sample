package br.com.pact.accountservice.account.infrastructure

import br.com.pact.accountservice.account.core.Account
import br.com.pact.accountservice.account.core.ports.AccountRepository
import java.util.UUID

class AccountRepositoryAdapter : AccountRepository {

    private val accounts = listOf<Account>()

    override fun findByPersonId(personId: String) =
        accounts.find { account -> account.personId == UUID.fromString(personId) }
}
