package com.example.cursobranasddd.infra.configuration.usecases

import com.example.cursobranasddd.core.application.GetAccountUseCase
import com.example.cursobranasddd.core.domain.entity.account.AccountRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GetAccountUseCaseConfiguration(
    private val accountRepository: AccountRepository,
) {

    @Bean
    fun getAccountUseCase() = GetAccountUseCase(
        accountRepository
    )
}
