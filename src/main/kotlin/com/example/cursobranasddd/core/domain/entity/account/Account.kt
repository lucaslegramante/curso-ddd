package com.example.cursobranasddd.core.domain.entity.account

import com.example.cursobranasddd.core.domain.entities.AggregateRoot
import com.example.cursobranasddd.core.domain.entities.Identifier
import com.example.cursobranasddd.core.domain.support.InstantUtils
import com.example.cursobranasddd.core.domain.vo.CarPlate
import com.example.cursobranasddd.core.domain.vo.Cpf
import com.example.cursobranasddd.core.domain.vo.Email
import com.example.cursobranasddd.core.domain.vo.Name
import java.time.Instant
import java.util.UUID

class Account private constructor(
    override val id: Identifier,
    val name: Name,
    val email: Email,
    val cpf: Cpf,
    val carPlate: CarPlate,
    val isPassenger: Boolean,
    val isDriver: Boolean,
    val createdAt: Instant,
    var updatedAt: Instant,
) : AggregateRoot<Identifier>(id) {

    companion object {
        fun new(
            name: Name,
            email: Email,
            cpf: Cpf,
            carPlate: CarPlate,
            isPassenger: Boolean,
            isDriver: Boolean,
        ) = Pair(Identifier.unique(), InstantUtils.now()).let { (id, now) ->
            Account(
                id = id,
                name = name,
                email = email,
                cpf = cpf,
                carPlate = carPlate,
                isPassenger = isPassenger,
                isDriver = isDriver,
                createdAt = now,
                updatedAt = now,
            )
        }

        fun restore(
            id: UUID,
            name: Name,
            email: Email,
            cpf: Cpf,
            carPlate: CarPlate,
            isPassenger: Boolean,
            isDriver: Boolean,
            createdAt: Instant,
            updatedAt: Instant,
        ): Account = Account(
            id = Identifier.from(id),
            name = name,
            email = email,
            cpf = cpf,
            carPlate = carPlate,
            isPassenger = isPassenger,
            isDriver = isDriver,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }
}
