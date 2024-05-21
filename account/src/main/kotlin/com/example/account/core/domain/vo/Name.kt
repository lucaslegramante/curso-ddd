package com.example.account.core.domain.vo

import com.example.account.core.domain.entities.ValueObject


class Name private constructor(private val name: String) : ValueObject {
    companion object {
        // funcao de restaurar do banco
        fun restore(name: String): Name {
            return Name(name)
        }

        // funcao de new e validando os dados

        fun new(name: String): Name {
            require(name.matches(Regex("^[a-zA-ZÀ-ÿ\u00f1\u00d1\\s'-]+$"))) { throw Exception("Invalid name") }
            return Name(name)
        }
    }

    fun getValue(): String { return this.name }
}
