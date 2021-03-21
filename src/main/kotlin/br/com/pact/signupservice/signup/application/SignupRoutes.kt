package br.com.pact.signupservice.signup.application

import io.ktor.application.call
import io.ktor.routing.Routing
import io.ktor.routing.post
import io.ktor.routing.route

fun Routing.signup(controller: SignupController) {

    route("/signup") {
        post { controller.signup(call) }
    }
}
