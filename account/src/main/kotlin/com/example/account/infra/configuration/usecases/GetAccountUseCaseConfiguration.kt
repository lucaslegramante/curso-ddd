package com.example.account.infra.configuration.usecases

import com.example.account.core.application.GetAccountUseCase
import com.example.account.core.domain.entity.account.AccountRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GetAccountUseCaseConfiguration(
    private val accountRepository: AccountRepository,
) {
    @Bean
    fun getAccountUseCase() =
        GetAccountUseCase(
            accountRepository,
        )
}
