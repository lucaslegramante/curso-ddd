package com.example.account.core.domain.vo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class NameTest {
    @Test
    fun `should create a name`() {

        val name = Name.new("John Doe")

        assertThat(name.getValue()).isEqualTo("John Doe")
    }

    @Test
    fun `should throw exception when name is invalid`() {

        val exception = assertThrows<Exception> { Name.new("2313123") }
        assertThat(exception.message).isEqualTo("Invalid name")


    }
}
