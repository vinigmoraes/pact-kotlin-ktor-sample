package br.com.pact.accountservice.account.core

import java.math.BigDecimal
import java.util.UUID

class Account(
    val id: UUID = UUID.randomUUID(),
    val personId: UUID,
    val balance: BigDecimal = BigDecimal(0)
) {
    companion object {
        fun create(personId: String) = Account(
            personId = UUID.fromString(personId)
        )
    }
}
