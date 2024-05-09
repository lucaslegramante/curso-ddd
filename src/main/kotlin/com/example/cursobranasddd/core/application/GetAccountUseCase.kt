package com.example.cursobranasddd.core.application

import com.example.cursobranasddd.core.domain.entities.Identifier
import com.example.cursobranasddd.core.domain.entity.account.AccountRepository

@UseCaseAnnotation
class GetAccountuUseCase(
    private val accountRepository: AccountRepository
) {
    fun execute(input: GetAccountCommand) {
        val account = accountRepository.findById(Identifier.from(input.id))
        return {
            accountId: account.accountId,
            name: account.getName(),
            email: account.getEmail(),
            cpf: account.getCpf(),
            carPlate: account.getCarPlate(),
            isPassenger: account.isPassenger,
            isDriver: account.isDriver
        }
}
}

data class GetAccountCommand(
    val id: String
)

data class GetAccountResponse(
    val accountId: String,
    val name: String,
    val email: String,
    val cpf: String,
    val carPlate: String,
    val isPassenger: Boolean,
    val isDriver: Boolean
)
