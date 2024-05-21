package com.example.account.infra.account

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface AccountRepositoryJpa : JpaRepository<AccountModel, UUID> {

    @Query("SELECT a FROM AccountModel a WHERE a.email = :email")
    fun findByEmail(email: String): AccountModel?
}
