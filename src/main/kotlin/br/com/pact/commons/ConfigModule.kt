package br.com.pact.commons

import br.com.pact.signupservice.signup.application.setup.ObjectMapperProvider
import org.koin.dsl.module

val configModule = module {
    single { ObjectMapperProvider.provide() }
}
