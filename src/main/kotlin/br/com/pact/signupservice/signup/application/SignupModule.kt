package br.com.pact.signupservice.signup.application

import br.com.pact.signupservice.signup.core.SignupService
import br.com.signupservice.signup.core.ports.DataValidation
import br.com.signupservice.signup.infrastructure.CloudMersiveDataValidation
import org.koin.dsl.module

val signupModule = module {
    single { SignupController(get()) }
    single { SignupService(get()) }
    single<DataValidation> { CloudMersiveDataValidation("8ba63345-96c2-423b-8e3d-ed0f97d06dd9") }
}
