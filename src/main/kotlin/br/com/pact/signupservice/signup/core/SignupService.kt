package br.com.pact.signupservice.signup.core

import br.com.pact.signupservice.signup.core.person.Person
import br.com.signupservice.signup.core.ports.DataValidation
import br.com.signupservice.signup.core.ports.SignupDTO

class SignupService(
    private val dataValidation: DataValidation
) {

    fun signup(dto: SignupDTO): Person {
        return Person
            .create(dto)
            .also { dataValidation.validate(it) }
    }
}
