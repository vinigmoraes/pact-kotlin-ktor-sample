package br.com.pact.accountservice.account.application

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveText
import io.ktor.response.respond
import br.com.pact.accountservice.account.application.requests.CreateAccountRequest
import br.com.pact.accountservice.account.application.responses.AccountCreatedResponse
import br.com.pact.accountservice.account.application.responses.AccountFoundResponse
import br.com.pact.accountservice.account.core.Account
import br.com.pact.accountservice.account.core.AccountService

class AccountController(
    private val mapper: ObjectMapper,
    private val service: AccountService
) {

    suspend fun create(call: ApplicationCall) {
        val request = call
            .receiveText()
            .let { mapper.readValue<CreateAccountRequest>(it) }

        val account = service.create(request.personId)

        call.respond(HttpStatusCode.Created, AccountCreatedResponse(id = account.id, balance = account.balance))
    }

    suspend fun get(call: ApplicationCall) {
        val personId = call.parameters["personId"]!!

        val account = service.findByPersonId(personId)

        call.respond(HttpStatusCode.OK, AccountFoundResponse.create(account))
    }
}
