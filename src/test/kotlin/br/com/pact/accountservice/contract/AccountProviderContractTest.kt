package br.com.pact.accountservice.contract

import au.com.dius.pact.provider.junit.Provider
import au.com.dius.pact.provider.junit.State
import au.com.dius.pact.provider.junit.VerificationReports
import au.com.dius.pact.provider.junit.loader.PactBroker
import au.com.dius.pact.provider.junit5.HttpTestTarget
import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith
import br.com.pact.accountservice.IntegrationTest

private const val accountService = "account-service"

@Provider(accountService)
@PactBroker(host = "localhost", port = "9292")
@VerificationReports
class AccountProviderContractTest : IntegrationTest() {

    companion object {
        @JvmStatic
        @BeforeAll
        fun enablePublishingPact() {
            System.setProperty("pact.verifier.publishResults", "true")
        }
    }

    @BeforeEach
    fun setUp(context: PactVerificationContext) {
        context.target = HttpTestTarget("localhost", 8081, "/")
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider::class)
    fun testTemplate(context: PactVerificationContext) {
        context.verifyInteraction()
    }

    @State("Get account")
    fun getAccount() {}
}
