package br.com.signupservice.signup.infrastructure

import br.com.pact.signupservice.signup.core.person.Person
import br.com.signupservice.signup.core.ports.DataValidation
import com.cloudmersive.client.EmailApi
import com.cloudmersive.client.invoker.ApiClient

class CloudMersiveDataValidation(
    private val apiKey: String
) : DataValidation {

    private val apiClient = ApiClient().also { it.setApiKey(apiKey) }
    private val emailApi = EmailApi(apiClient)

    override fun validate(person: Person) {
        validateEmail(person.email)
    }

    private fun validateEmail(email: String) =
        require(emailApi.emailFullValidation(email).isValidAddress) { throw Exception("Invalid Email") }
}
