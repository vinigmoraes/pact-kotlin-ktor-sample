package br.com.pact.accountservice.account.core.exceptions

import br.com.pact.commons.exceptions.ContractTestApplicationException
import br.com.pact.commons.exceptions.errors.ErrorResponse
import io.ktor.http.HttpStatusCode

class AccountAlreadyExists(
    private val personId: String
) : ContractTestApplicationException() {

    override fun response() = ErrorResponse.create("Account already exist for personId: $personId")

    override fun statusCode() = HttpStatusCode.UnprocessableEntity
}
