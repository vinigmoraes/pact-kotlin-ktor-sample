package br.com.signupservice.signup.core.ports

import br.com.pact.signupservice.signup.core.person.Person

interface DataValidation {

    fun validate(person: Person)
}
