package com.example.cursobranasddd.core.application

import com.example.cursobranasddd.builders.AccountBuilder
import com.example.cursobranasddd.core.domain.entity.account.AccountRepository
import io.mockk.every
import org.junit.jupiter.api.Assertions.*
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

internal class GetAccountUseCaseTest {
    private val accountRepository = mockk<AccountRepository>(relaxed = true)

    private val subject = GetAccountUseCase(accountRepository)

    @Test
    fun `should get account by id`() {
        val accountId = UUID.randomUUID()

        val account = AccountBuilder.build(id = accountId)

        every { accountRepository.findById(any()) } returns account

        val result = subject.execute(GetAccountCommand(accountId))

        assertThat(result.name).isEqualTo(account.name.getValue())
        assertThat(result.accountId).isEqualTo(account.id.toUUID())
        assertThat(result.email).isEqualTo(account.email.getValue())
        assertThat(result.cpf).isEqualTo(account.cpf.getValue())
        assertThat(result.isDriver).isEqualTo(account.isDriver)
        assertThat(result.isPassenger).isEqualTo(account.isPassenger)
        assertThat(result.carPlate).isEqualTo(account.carPlate.getValue())
    }

    @Test
    fun `should throws exception when account not found `() {
        val accountId = UUID.randomUUID()

        every { accountRepository.findById(any()) } returns null

        val exception = assertThrows<Exception> { subject.execute(GetAccountCommand(accountId)) }

        assertThat(exception.message).isEqualTo("Account not found")
    }
}
