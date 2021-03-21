package br.com.pact.signupservice.signup.core.person

import br.com.signupservice.signup.core.ports.SignupDTO
import java.util.UUID

data class Person(
    val name: String,
    val taxId: String,
    val email: String
) {

    val id = UUID.randomUUID()

    companion object {
        fun create(dto: SignupDTO) = Person(name = dto.name, email = dto.email, taxId = dto.taxId)
    }
}
