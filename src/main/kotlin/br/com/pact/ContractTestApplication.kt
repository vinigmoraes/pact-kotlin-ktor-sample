package br.com.pact

import br.com.pact.signupservice.signup.application.setup.ObjectMapperProvider
import br.com.pact.signupservice.signup.application.signup
import br.com.pact.signupservice.signup.application.signupModule
import br.com.pact.accountservice.account.application.accountModule
import br.com.pact.accountservice.account.application.accounts
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.jackson.JacksonConverter
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.netty.NettyApplicationEngine
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.get
import br.com.pact.commons.configModule
import br.com.pact.commons.exceptions.ContractTestApplicationException
import io.ktor.application.call
import io.ktor.features.StatusPages
import io.ktor.response.respond

fun main() {
    embeddedServer(Netty, System.getenv("PORT")?.toInt() ?: 8080) {
        main()
    }.start(wait = true)
}

object Application {

    fun start(port: Int): NettyApplicationEngine = embeddedServer(
        factory = Netty,
        watchPaths = listOf("src/main/kotlin/br.com.pact"),
        port = port,
        module = Application::main
    ).start(false)
}

fun Application.main() {

    install(StatusPages) {
        exception<ContractTestApplicationException> { call.respond(it.statusCode(), it.response()) }
    }

    install(ContentNegotiation) {
        register(ContentType.Application.Json, JacksonConverter(ObjectMapperProvider.provide()))
    }

    install(Koin) {
        modules(listOf(configModule, signupModule, accountModule))
    }

    routing {
        accounts(get())
        signup(get())
    }
}

