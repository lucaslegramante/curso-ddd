package com.example.cursobranasddd.core.domain.entity.entities

import br.com.creditas.fundingeligibility.core.domain.entities.ValueObject
import java.util.Locale
import java.util.UUID

class Identifier(
    val value: String,
) : ValueObject {

    fun getValue(): Identifier { return this }

    init {
        runCatching {
            UUID.fromString(value)
        }.onFailure {
            throw Error("ID: $value invalid")
        }
    }

    companion object {
        fun unique(): Identifier = from(UUID.randomUUID())
        fun from(id: String): Identifier = Identifier(id)
        fun from(id: UUID): Identifier = Identifier(id.toString().lowercase(Locale.getDefault()))
    }
}
