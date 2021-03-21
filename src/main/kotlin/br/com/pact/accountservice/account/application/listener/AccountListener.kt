package br.com.pact.accountservice.account.application.listener

import br.com.pact.accountservice.account.application.requests.CreateAccountRequest
import br.com.pact.accountservice.account.core.AccountService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

class AccountListener(
    private val mapper: ObjectMapper,
    private val service: AccountService
) {

    fun createAccount(message: String) {
        val request = mapper.readValue<CreateAccountRequest>(message)

        service.create(request.personId)
    }
}
