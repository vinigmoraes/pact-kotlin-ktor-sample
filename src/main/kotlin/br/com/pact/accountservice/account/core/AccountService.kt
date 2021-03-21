package br.com.pact.accountservice.account.core

import br.com.pact.accountservice.account.core.exceptions.AccountNotFoundException
import br.com.pact.accountservice.account.core.exceptions.AccountAlreadyExists
import br.com.pact.accountservice.account.core.ports.AccountRepository

class AccountService(
    private val repository: AccountRepository
) {

    fun create(personId: String): Account {
        require(repository.findByPersonId(personId) == null) { throw AccountAlreadyExists(personId) }

        return Account.create(personId)
    }

    fun findByPersonId(personId: String) = repository.findByPersonId(personId) ?: throw AccountNotFoundException(personId)
}
