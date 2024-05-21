package com.example.account.core.domain.vo

import com.example.account.core.domain.entities.ValueObject

class CarPlate private constructor(val carPlate: String) : ValueObject {
    fun getValue(): String {
        return this.carPlate
    }

    companion object {
        fun restore(carPlate: String): CarPlate {
            return CarPlate(carPlate)
        }

        fun new(carPlate: String): CarPlate {
            require(carPlate.matches(Regex("^[A-Z]{3}[0-9]{1}[A-Z]?[0-9]{2,3}\$"))) { throw Error("Invalid car plate") }
            return CarPlate(carPlate)
        }
    }
}
