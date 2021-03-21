package br.com.pact.accountservice.account.core.ports

import br.com.pact.accountservice.account.core.Account

interface AccountRepository {

    fun findByPersonId(personId: String): Account?
}
