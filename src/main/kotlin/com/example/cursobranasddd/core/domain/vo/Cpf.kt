package com.example.cursobranasddd.core.domain.vo

class Cpf private constructor(private val cpf: String) {

    fun getValue(): String { return this.cpf }

    companion object {
        private val FACTOR_FIRST_DIGIT = 10
        private val FACTOR_SECOND_DIGIT = 11

        fun restore(cpf: String): Cpf {
            return Cpf(cpf)
        }

        fun new(cpf: String): Cpf {
            require(!validate(cpf)) { throw Error("Invalid cpf") }
            return Cpf(removeNonDigits(cpf))
        }

        private fun validate(rawCpf: String): Boolean {
            if (rawCpf.isBlank()) return false
            val cpf = removeNonDigits(rawCpf)
            if (!isValidLength(cpf)) return false
            if (allDigitsEqual(cpf)) return false
            val firstDigit = calculateDigit(cpf, FACTOR_FIRST_DIGIT)
            val secondDigit = calculateDigit(cpf, FACTOR_SECOND_DIGIT)
            return extractDigit(cpf) === "${firstDigit}$secondDigit"
        }

        private fun removeNonDigits(cpf: String): String {
            return cpf.replace(Regex("[^0-9]"), "")
        }

        private fun isValidLength(cpf: String): Boolean {
            return cpf.length == 11
        }

        private fun allDigitsEqual(cpf: String): Boolean {
            val firstDigit = cpf.first()
            return cpf.all { it == firstDigit }
        }

        private fun calculateDigit(cpf: String, factor: Int): Int {
            var total = 0
            var factorCopy = factor
            for (char in cpf) {
                if (factorCopy > 1) {
                    total += Character.getNumericValue(char) * factorCopy
                    factorCopy--
                }
            }
            val remainder = total % 11
            return if (remainder < 2) 0 else 11 - remainder
        }

        private fun extractDigit(cpf: String): String {
            return cpf.substring(9)
        }
    }
}
