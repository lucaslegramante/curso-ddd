package com.example.account.core.application

import com.example.account.core.domain.entities.Identifier
import com.example.account.core.domain.entity.account.AccountRepository
import java.util.UUID

@UseCaseAnnotation
class GetAccountUseCase(
    private val accountRepository: AccountRepository
) : UseCase<GetAccountCommand, GetAccountResponse> {
    override fun execute(input: GetAccountCommand): GetAccountResponse {
        val account = accountRepository.findById(Identifier.from(input.id)) ?: throw Exception("Account not found")
        return GetAccountResponse(
            accountId = account.id.toUUID(),
            name = account.name.getValue(),
            email = account.email.getValue(),
            cpf = account.cpf.getValue(),
            carPlate = account.carPlate.getValue(),
            isPassenger = account.isPassenger,
            isDriver = account.isDriver
        )
    }
}

data class GetAccountCommand(
    val id: UUID
)

data class GetAccountResponse(
    val accountId: UUID,
    val name: String,
    val email: String,
    val cpf: String,
    val carPlate: String,
    val isPassenger: Boolean,
    val isDriver: Boolean
)
