package br.com.pact.accountservice.contract.consumer

import au.com.dius.pact.consumer.MessagePactBuilder
import au.com.dius.pact.consumer.dsl.PactDslJsonBody
import au.com.dius.pact.consumer.junit.PactVerification
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import au.com.dius.pact.consumer.junit5.ProviderType
import au.com.dius.pact.core.model.annotations.Pact
import au.com.dius.pact.core.model.messaging.Message
import au.com.dius.pact.core.model.messaging.MessagePact
import br.com.pact.accountservice.account.application.listener.AccountListener
import br.com.pact.accountservice.account.core.Account
import br.com.pact.accountservice.account.core.AccountService
import br.com.pact.signupservice.signup.application.setup.ObjectMapperProvider
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

private const val accountService = "account-service"
private const val signupService = "signup-service"

@ExtendWith(PactConsumerTestExt::class)
@PactTestFor(providerName = signupService, providerType = ProviderType.ASYNCH)
class AccountConsumerContractTest {

    private val mapper = ObjectMapperProvider.provide()
    private val service = mockk<AccountService>()
    private val listener = AccountListener(mapper, service)

    @Pact(provider = signupService, consumer = accountService)
    fun createAccountMessage(builder: MessagePactBuilder): MessagePact {
        val json = PactDslJsonBody()
            .uuid("person_id")

        return builder
            .given("Create account")
            .expectsToReceive("message with person id")
            .withContent(json)
            .toPact()
    }

    @Test
    @PactVerification("createAccountMessage")
    fun verifyCreateAccountMessage(messages: List<Message>) {
        val account = Account.create("cc8aa39f-39f4-4008-8ad6-f8b396efc6c9")

        every { service.create(any()) } returns account

        messages.forEach { message ->
            listener.createAccount(message.contentsAsString())

            verify(exactly = 1) { service.create(any()) }
        }
    }
}
