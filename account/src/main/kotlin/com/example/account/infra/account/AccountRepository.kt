package com.example.account.infra.account

import com.example.account.core.domain.entities.Identifier
import com.example.account.core.domain.entity.account.Account
import com.example.account.core.domain.entity.account.AccountRepository
import com.example.account.core.domain.vo.Email
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
open class AccountRepository(
    private val accountRepositoryJpa: AccountRepositoryJpa,
) : AccountRepository {
    @Transactional(rollbackFor = [Exception::class])
    override fun save(account: Account): Account {
        return accountRepositoryJpa.save(AccountModel.from(account)).restoreToAggregateRoot()
    }

    override fun findByEmail(email: Email): Account? {
        return accountRepositoryJpa.findByEmail(email.getValue())?.restoreToAggregateRoot()
    }

    override fun findById(id: Identifier): Account? {
        return accountRepositoryJpa.findByIdOrNull(id.toUUID())?.restoreToAggregateRoot()
    }
}
