package br.com.pact.accountservice

import br.com.pact.Application
import io.ktor.server.netty.NettyApplicationEngine
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import java.util.concurrent.TimeUnit

abstract class IntegrationTest {

    companion object {
        private lateinit var server: NettyApplicationEngine

        @BeforeAll
        @JvmStatic
        fun setUpClass() {
            server = Application.start(8081)
        }

        @AfterAll
        @JvmStatic
        fun tearDownClass() {
            server.stop(5, 10, TimeUnit.SECONDS)
        }
    }
}
