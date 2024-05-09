package com.example.cursobranasddd.core.domain.entities

abstract class Entity<ID : Identifier> protected constructor(open val id: ID)
