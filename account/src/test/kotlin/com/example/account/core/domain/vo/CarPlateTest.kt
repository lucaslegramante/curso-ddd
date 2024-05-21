package com.example.account.core.domain.vo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class CarPlateTest {
    @Test
    fun `should create a car plate`() {

        val carPlate = CarPlate.new("AAA9999")

        assertThat(carPlate.getValue()).isEqualTo("AAA9999")
    }

    @Test
    fun `should throw exception when carPlate is invalid`() {

        val exception = assertThrows<Error> { CarPlate.new("2313123") }
        assertThat(exception.message).isEqualTo("Invalid car plate")
    }
}
