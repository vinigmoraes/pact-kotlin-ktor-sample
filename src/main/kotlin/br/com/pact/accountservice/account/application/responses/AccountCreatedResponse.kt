package br.com.pact.accountservice.account.application.responses

import java.math.BigDecimal
import java.util.UUID

data class AccountCreatedResponse(
    val id: UUID,
    val balance: BigDecimal
)
