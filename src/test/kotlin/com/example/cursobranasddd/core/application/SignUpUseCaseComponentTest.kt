package com.example.cursobranasddd.core.application

import com.example.cursobranasddd.core.domain.entity.account.AccountRepository
import com.example.cursobranasddd.core.domain.gateway.MailerGateway
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class SignUpUseCaseTest {
    @Autowired
    private val accountRepository: AccountRepository

    private val mailerGateway = mockk<MailerGateway>(relaxed = true)

    private val subject = SignUpUseCase(accountRepository, mailerGateway)

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

        assertEquals(1, accountRepository.findAll().size)
        assertEquals(1, mailerGateway.invocationsCount)
    }
}
