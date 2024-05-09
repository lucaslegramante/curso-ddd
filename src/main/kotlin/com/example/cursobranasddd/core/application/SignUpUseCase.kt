package com.example.cursobranasddd.core.application

import com.example.cursobranasddd.core.domain.entity.account.Account
import com.example.cursobranasddd.core.domain.entity.account.AccountRepository
import com.example.cursobranasddd.core.domain.gateway.MailerGateway
import com.example.cursobranasddd.core.domain.vo.CarPlate
import com.example.cursobranasddd.core.domain.vo.Cpf
import com.example.cursobranasddd.core.domain.vo.Email
import com.example.cursobranasddd.core.domain.vo.Name
import java.util.UUID

@UseCaseAnnotation
class SignUpUseCase(
    private val accountRepository: AccountRepository,
    private val mailerGateway: MailerGateway
) : UseCase<SignUpCommand, SignUpResponse> {
    override fun execute(input: SignUpCommand): SignUpResponse {
        val existingAccount = accountRepository.findByEmail(Email.restore(input.email))
        if (existingAccount != null) throw Exception("Account already exists")

        val account = Account.new(
            name = Name.new(input.name),
            email = Email.new(input.email),
            cpf = Cpf.new(input.cpf),
            carPlate = CarPlate.new(input.carPlate),
            isPassenger = input.isPassenger ?: false,
            isDriver = input.isDriver ?: false
        )

        accountRepository.save(account)
        mailerGateway.send(account.email.getValue(), "Welcome!", "")
        return SignUpResponse(
            accountId = account.id.toUUID()
        )
    }
}

data class SignUpResponse(
    val accountId: UUID
)

data class SignUpCommand(
    val name: String,
    val email: String,
    val cpf: String,
    val carPlate: String,
    val isPassenger: Boolean,
    val isDriver: Boolean
)
