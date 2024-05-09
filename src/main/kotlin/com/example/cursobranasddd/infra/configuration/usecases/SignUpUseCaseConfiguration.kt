package br.com.creditas.fundingeligibility.infrastructure.configuration.usecases

import br.com.creditas.fundingeligibility.core.application.collateral.create.CollateralCreationUseCase
import br.com.creditas.fundingeligibility.core.application.collateral.create.MissingCollateralsCreationUseCase
import br.com.creditas.fundingeligibility.core.domain.collateral.CollateralGateway
import br.com.creditas.fundingeligibility.core.domain.collateral.CollateralRepository
import br.com.creditas.fundingeligibility.core.domain.events.IEventDispatcher
import br.com.creditas.fundingeligibility.core.domain.loan.LoanRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CollateralUseCaseConfiguration(
    private val collateralRepository: CollateralRepository,
    private val collateralGateway: CollateralGateway,
    private val loanRepository: LoanRepository,
    private val eventDispatcher: IEventDispatcher
) {

    @Bean
    fun collateralCreationUseCase() = CollateralCreationUseCase(
        collateralRepository,
        collateralGateway,
        loanRepository,
        eventDispatcher
    )

    @Bean
    fun missingCollateralsCreationUseCase() = MissingCollateralsCreationUseCase(
        loanRepository,
        collateralGateway,
        collateralRepository,
        eventDispatcher
    )
}
