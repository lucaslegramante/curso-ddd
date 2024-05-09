package com.example.cursobranasddd.core.domain.entity.entities

abstract class Entity<ID : Identifier> protected constructor(open val id: ID)
