package br.com.pact.signupservice.contract.provider

import au.com.dius.pact.provider.PactVerifyProvider
import au.com.dius.pact.provider.junit.Provider
import au.com.dius.pact.provider.junit.State
import au.com.dius.pact.provider.junit.VerificationReports
import au.com.dius.pact.provider.junit.loader.PactBroker
import au.com.dius.pact.provider.junit5.AmpqTestTarget
import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider
import br.com.pact.accountservice.account.application.requests.CreateAccountRequest
import br.com.pact.signupservice.signup.application.setup.ObjectMapperProvider
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith
import java.util.Collections

private const val signupService = "signup-service"

@Provider(signupService)
@PactBroker(host = "localhost", port = "9292")
@VerificationReports
class SignupProviderContractTest {

    private val mapper = ObjectMapperProvider.provide()

    companion object {
        @JvmStatic
        @BeforeAll
        fun enablePublishingPact() {
            System.setProperty("pact.verifier.publishResults", "true")
        }
    }

    @BeforeEach
    fun before(context: PactVerificationContext) {
        context.target = AmpqTestTarget(Collections.emptyList())
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider::class)
    fun testTemplate(context: PactVerificationContext) {
        context.verifyInteraction()
    }

    @PactVerifyProvider("A message to create account")
    fun provideCreateAccountMessage(): String {
        return mapper.writeValueAsString(CreateAccountRequest(personId = "e2490de5-5bd3-43d5-b7c4-526e33f71304"))
    }

    @State("Create account message")
    fun createAccount() {}
}
