package com.example.account.core.domain.entities

import java.util.Locale
import java.util.UUID

class Identifier(
    val value: String,
) : ValueObject {

    fun getValue(): Identifier { return this }

    fun toUUID() = UUID.fromString(value)

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
