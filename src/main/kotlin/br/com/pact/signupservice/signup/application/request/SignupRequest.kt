package br.com.pact.signupservice.signup.application.request

import br.com.signupservice.signup.core.ports.SignupDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignupRequest(
    val name: String,
    @SerialName("tax_id")
    val taxId: String,
    val email: String
) {
    fun toSignupDTO() = SignupDTO(name, taxId, email)
}
