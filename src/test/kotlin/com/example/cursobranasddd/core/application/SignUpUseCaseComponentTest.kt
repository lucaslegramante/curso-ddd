package com.example.cursobranasddd.core.application

import com.example.cursobranasddd.CursoBranasDddApplication
import com.example.cursobranasddd.core.domain.entity.account.AccountRepository
import com.example.cursobranasddd.core.domain.gateway.MailerGateway
import com.example.cursobranasddd.core.domain.vo.Email
import com.example.cursobranasddd.infra.account.AccountRepositoryJpa
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [CursoBranasDddApplication::class])
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SignUpUseCaseComponentTest {
    @Autowired
    private lateinit var accountRepository: AccountRepository

    @Autowired
    private lateinit var accountRepositoryJpa: AccountRepositoryJpa

    @Autowired
    private lateinit var mailerGateway: MailerGateway

    @Autowired
    private lateinit var subject: SignUpUseCase

    @AfterEach
    fun tearDown() {
        accountRepositoryJpa.deleteAll()
    }

    @Test
    fun `should create a new account and send email`() {
        val command = SignUpCommand(
            name = "Joaquim",
            email = "lucas${Math.random()}@example.com",
            cpf = "97456321558",
            carPlate = "AAA9999",
            isPassenger = true,
            isDriver = false
        )
        subject.execute(command)

        val result = accountRepository.findByEmail(Email.restore(command.email))!!

        assertEquals(result.email.getValue(), command.email)
        assertEquals(result.cpf.getValue(), command.cpf)
        assertEquals(result.name.getValue(), command.name)
        assertEquals(result.carPlate.getValue(), command.carPlate)
        assertEquals(result.isDriver, command.isDriver)
        assertEquals(result.isPassenger, command.isPassenger)
    }
}
