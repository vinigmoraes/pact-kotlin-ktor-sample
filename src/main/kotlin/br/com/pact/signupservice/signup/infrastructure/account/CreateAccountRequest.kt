package br.com.pact.signupservice.signup.infrastructure.account

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateAccountRequest(
    @JsonProperty("person_id")
    val personId: String
)
