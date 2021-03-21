package br.com.pact.accountservice.account.core.exceptions

import br.com.pact.commons.exceptions.ContractTestApplicationException
import br.com.pact.commons.exceptions.errors.ErrorResponse
import io.ktor.http.HttpStatusCode

class AccountNotFoundException(
    private val personId: String
) : ContractTestApplicationException() {

    override fun response() = ErrorResponse.create("Account with personId: $personId not found")

    override fun statusCode() = HttpStatusCode.NotFound
}
