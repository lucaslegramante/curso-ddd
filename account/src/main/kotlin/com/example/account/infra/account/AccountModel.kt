package com.example.account.infra.account

import com.example.account.core.domain.entity.account.Account
import com.example.account.core.domain.vo.CarPlate
import com.example.account.core.domain.vo.Cpf
import com.example.account.core.domain.vo.Email
import com.example.account.core.domain.vo.Name
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID


@Entity
@Table(name = "account")
class AccountModel(
    @Id
    val id: UUID,

    @Column(name = "name")
    val name: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "cpf")
    val cpf: String,

    @Column(name = "car_plate")
    val carPlate: String,

    @Column(name = "is_passenger")
    val isPassenger: Boolean,

    @Column(name = "is_driver")
    val isDriver: Boolean,

    @Column(name = "created_at")
    val createdAt: Instant,

    @Column(name = "updated_at")
    var updatedAt: Instant,
) {
    companion object {
        fun from(account: Account): AccountModel = with(account) {
            AccountModel(
                id = id.toUUID(),
                name = name.getValue(),
                email = email.getValue(),
                cpf = cpf.getValue(),
                carPlate = carPlate.getValue(),
                isPassenger = isPassenger,
                isDriver = isDriver,
                createdAt = createdAt,
                updatedAt = updatedAt,
            )
        }
    }
    fun restoreToAggregateRoot() = Account.restore(
        id = id,
        name = Name.restore(name),
        email = Email.restore(email),
        cpf = Cpf.restore(cpf),
        carPlate = CarPlate.restore(carPlate),
        isPassenger = isPassenger,
        isDriver = isDriver,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
