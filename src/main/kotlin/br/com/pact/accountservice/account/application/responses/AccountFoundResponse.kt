package br.com.pact.accountservice.account.application.responses

import br.com.pact.accountservice.account.core.Account
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.util.UUID

data class AccountFoundResponse(
    @JsonProperty("id")
    val id: UUID,
    @JsonProperty("person_id")
    val personId: UUID,
    @JsonProperty("balance")
    val balance: BigDecimal
) {
    companion object {
        fun create(account: Account) = AccountFoundResponse(
            id = account.id,
            personId = account.personId,
            balance = account.balance
        )
    }
}
