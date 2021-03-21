package br.com.pact.signupservice.signup.application

import br.com.pact.signupservice.signup.application.request.SignupRequest
import br.com.pact.signupservice.signup.application.response.SignupResponse
import br.com.pact.signupservice.signup.core.SignupService
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveText
import io.ktor.response.respond
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SignupController(
    private val service: SignupService
) {

    suspend fun signup(call: ApplicationCall) {
        val request = call
            .receiveText()
            .let { Json.decodeFromString<SignupRequest>(it) }

        val response = service
            .signup(request.toSignupDTO())
            .let { Json.encodeToString(SignupResponse(id = it.id)) }

        call.respond(HttpStatusCode.Created, response)
    }
}
