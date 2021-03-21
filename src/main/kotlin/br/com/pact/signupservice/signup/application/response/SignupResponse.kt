package br.com.pact.signupservice.signup.application.response

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class SignupResponse(
    @get:JsonProperty("id")
    val id: UUID
)
