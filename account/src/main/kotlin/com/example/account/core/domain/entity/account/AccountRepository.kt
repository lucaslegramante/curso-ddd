package com.example.account.core.domain.entity.account

import com.example.account.core.domain.entities.Identifier
import com.example.account.core.domain.vo.Email

interface AccountRepository {
    fun save(account: Account): Account

    fun findByEmail(email: Email): Account?

    fun findById(id: Identifier): Account?
}
