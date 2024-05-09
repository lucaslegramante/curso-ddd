package com.example.cursobranasddd.core.domain.entity.entities


abstract class AggregateRoot<T> protected constructor(override val id: Identifier) : Entity<Identifier>(id)
