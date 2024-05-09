package com.example.cursobranasddd.infra.configuration.usecases

import com.example.cursobranasddd.core.application.SignUpUseCase
import com.example.cursobranasddd.core.domain.entity.account.AccountRepository
import com.example.cursobranasddd.core.domain.gateway.MailerGateway
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SignUpUseCaseConfiguration(
    private val accountRepository: AccountRepository,
    private val mailerGateway: MailerGateway
) {

    @Bean
    fun signUpUseCase() = SignUpUseCase(
        accountRepository,
        mailerGateway,
    )
}
