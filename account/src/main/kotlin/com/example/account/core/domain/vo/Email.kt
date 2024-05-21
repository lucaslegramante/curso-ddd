package com.example.account.core.domain.vo

import com.example.account.core.domain.entities.ValueObject

class Email private constructor(private val email: String): ValueObject {

//    init {
//        require(!email.matches(Regex("^(.+)@(.+)$"))) { throw Error("Invalid email") }
//    }

    fun getValue(): String { return this.email }

    companion object {
        fun restore(email: String): Email {
            return Email(email)
        }

        fun new(email: String): Email {
            require(email.matches(Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))) { throw Exception("Invalid email") }
            return Email(email)
        }
    }
}
