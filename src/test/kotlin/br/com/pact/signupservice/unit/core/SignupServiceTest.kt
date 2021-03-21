package br.com.pact.signupservice.unit.core

import br.com.pact.signupservice.signup.core.SignupService
import br.com.pact.signupservice.signup.core.person.Person
import br.com.signupservice.signup.core.ports.DataValidation
import br.com.signupservice.signup.core.ports.SignupDTO
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SignupServiceTest {

    private val dataValidation = mockk<DataValidation>()
    private val service = SignupService(dataValidation)

    @Test
    fun `given valid signup dto should create it successfully`() {
        val dto = SignupDTO(
            name = "Joao",
            email = "joao@email.com",
            taxId = "1289387912"
        )
        val expectedResponse = Person.create(dto)

        every { dataValidation.validate(any()) } just runs

        val person = service.signup(dto)

        assertEquals(expectedResponse, person)
    }
}
