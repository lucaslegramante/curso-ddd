package com.example.account.core.application

import com.example.account.core.domain.entities.Identifier
import com.example.account.core.domain.entity.account.AccountRepository
import java.util.UUID

@UseCaseAnnotation
class GetAccountUseCase(
    private val accountRepository: AccountRepository
) : UseCase<GetAccountCommand, Either<GetAccountData, AccountNotFoundException>> {
    override fun execute(input: GetAccountCommand): Either<GetAccountData, AccountNotFoundException> {
        val account = accountRepository.findById(Identifier.from(input.id))
            ?: return Either.Right(AccountNotFoundException(input.id.toString()))
        return Either.Left(
            GetAccountData(
                accountId = account.id.toUUID(),
                name = account.name.getValue(),
                email = account.email.getValue(),
                cpf = account.cpf.getValue(),
                carPlate = account.carPlate.getValue(),
                isPassenger = account.isPassenger,
                isDriver = account.isDriver
            )
        )
    }
}

sealed class Either<out A, out B> {
    class Left<A>(val value: A) : Either<A, Nothing>()
    class Right<B>(val value: B) : Either<Nothing, B>()

    val isLeft get() = this is Left<A>
    val isRight get() = this is Right<B>
}

data class AccountNotFoundException(val id: String) : Exception("Account with id $id not found")

data class GetAccountCommand(
    val id: UUID
)

data class GetAccountResponse(
    val data: GetAccountData? = null,
    val errors: String? = null
) {
    val hasErrors: Boolean
        get() = errors != null
}

data class GetAccountData(
    val accountId: UUID,
    val name: String,
    val email: String,
    val cpf: String,
    val carPlate: String,
    val isPassenger: Boolean,
    val isDriver: Boolean
)
