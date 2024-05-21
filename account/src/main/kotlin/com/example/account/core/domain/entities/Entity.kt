package com.example.account.core.domain.entities

abstract class Entity<ID : Identifier> protected constructor(open val id: ID)
