package br.com.pact.accountservice.account.application

import io.ktor.application.call
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route

fun Routing.accounts(controller: AccountController) {

    route("/accounts") {
        post { controller.create(call) }

        route("/{personId}") {
            get { controller.get(call) }
        }
    }
}
