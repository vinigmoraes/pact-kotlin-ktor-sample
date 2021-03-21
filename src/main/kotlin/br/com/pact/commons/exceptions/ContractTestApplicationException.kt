package br.com.pact.commons.exceptions

import br.com.pact.commons.exceptions.errors.ErrorResponse
import io.ktor.http.HttpStatusCode

abstract class ContractTestApplicationException : RuntimeException() {

    abstract fun response(): ErrorResponse

    abstract fun statusCode(): HttpStatusCode
}
