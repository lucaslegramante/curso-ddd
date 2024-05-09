package com.example.cursobranasddd.infra.account

import com.example.cursobranasddd.CursoBranasDddApplication
import com.example.cursobranasddd.builders.AccountBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [CursoBranasDddApplication::class])
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountRepositoryComponentTest {
    @Autowired
    private lateinit var subject: AccountRepository

    @Autowired
    private lateinit var accountRepositoryJpa: AccountRepositoryJpa

    @AfterEach
    fun tearDown() {
            accountRepositoryJpa.deleteAll()
        }

    @Test
    fun `should save account and find by id`() {

        val account = AccountBuilder.build()
        subject.save(account)

        val accountFound = subject.findById(account.id)

        assertThat(accountFound).isNotNull
        assertThat(accountFound!!.cpf.getValue()).isEqualTo(account.cpf.getValue())
    }

    @Test
    fun `should save account and find by email`() {

        val account = AccountBuilder.build()
        subject.save(account)

        val accountFound = subject.findByEmail(account.email)

        assertThat(accountFound).isNotNull
        assertThat(accountFound!!.email.getValue()).isEqualTo(account.email.getValue())
    }
}
