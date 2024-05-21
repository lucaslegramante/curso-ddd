package com.example.account.builders

import com.example.account.core.domain.entities.Identifier
import com.example.account.core.domain.entity.account.Account
import com.example.account.core.domain.support.InstantUtils
import com.example.account.core.domain.vo.CarPlate
import com.example.account.core.domain.vo.Cpf
import com.example.account.core.domain.vo.Email
import com.example.account.core.domain.vo.Name
import net.bytebuddy.utility.RandomString
import java.util.UUID

object AccountBuilder {
    fun build(
        id: UUID = UUID.randomUUID(),
        email: Email = Email.new("lucas${Math.random()}@example.com"),
    ): Account {
        return Account.restore(
            id = id,
            name = Name.restore("Joaquim ${RandomString()}"),
            email = email,
            cpf = Cpf.new("97456321558"),
            carPlate = CarPlate.new("AAA9999"),
            isPassenger = true,
            isDriver = false,
            createdAt = InstantUtils.now(),
            updatedAt = InstantUtils.now()
        )
    }
}
