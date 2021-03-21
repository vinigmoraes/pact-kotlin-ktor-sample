package br.com.pact.accountservice.account.application.requests

import com.fasterxml.jackson.annotation.JsonProperty

class CreateAccountRequest(
    @JsonProperty("person_id")
    val personId: String
)
