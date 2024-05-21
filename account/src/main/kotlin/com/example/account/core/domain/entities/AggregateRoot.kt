package com.example.account.core.domain.entities

abstract class AggregateRoot<T> protected constructor(override val id: Identifier) : Entity<Identifier>(id)
