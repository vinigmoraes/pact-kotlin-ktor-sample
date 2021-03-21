package br.com.pact.signupservice.contract.consumer

import au.com.dius.pact.consumer.MockServer
import au.com.dius.pact.consumer.dsl.PactDslJsonBody
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import au.com.dius.pact.core.model.RequestResponsePact
import au.com.dius.pact.core.model.annotations.Pact
import br.com.pact.signupservice.signup.application.setup.ObjectMapperProvider
import com.fasterxml.jackson.module.kotlin.readValue
import io.ktor.http.HttpStatusCode
import org.apache.http.client.fluent.Request
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

import br.com.pact.accountservice.account.application.responses.AccountFoundResponse
import org.junit.jupiter.api.DisplayName

private const val signupService = "signup-service"
private const val accountService = "account-service"

@ExtendWith(PactConsumerTestExt::class)
@PactTestFor(providerName = accountService)
class SignupConsumerContractTest {

    private val personId = "165ffbea-ea05-41e3-b7b5-d31b94afc878"
    private val accountsUrl = "/accounts"
    private val mapper = ObjectMapperProvider.provide()

    @BeforeEach
    fun setUp(mockServer: MockServer) {
        assertNotNull(mockServer)
    }

    @Pact(provider = accountService, consumer = signupService)
    fun getAccountTest(builder: PactDslWithProvider): RequestResponsePact {
        val bodyResponse = PactDslJsonBody()
            .uuid("id")
            .uuid("person_id")
            .numberType("balance")

        return builder
            .given("Get account")
            .uponReceiving("A request to $accountsUrl/$personId")
            .path("$accountsUrl/$personId")
            .method("GET")
            .willRespondWith()
            .status(HttpStatusCode.OK.value)
            .body(bodyResponse)
            .toPact()
    }

    @Test
    @PactTestFor(pactMethod = "getAccountTest", providerName = accountService)
    @DisplayName("When requesting to account service to get an account")
    fun testGetAccount(mockServer: MockServer) {
        val response = Request
            .Get(mockServer.getUrl() + "$accountsUrl/$personId")
            .execute()
            .returnResponse()

        val responseBody = String(response.entity.content.readAllBytes())

        val accountResponse = mapper.readValue<AccountFoundResponse>(responseBody)

        assertNotNull(accountResponse)
        assertEquals(response.statusLine.statusCode, 200)
    }
}
