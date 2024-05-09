package com.example.cursobranasddd.core.domain.entity.account

import com.example.cursobranasddd.core.domain.entities.Identifier
import com.example.cursobranasddd.core.domain.vo.Email

interface AccountRepository {
    fun save(account: Account): Account

    fun findByEmail(email: Email): Account?

    fun findById(id: Identifier): Account?
}
