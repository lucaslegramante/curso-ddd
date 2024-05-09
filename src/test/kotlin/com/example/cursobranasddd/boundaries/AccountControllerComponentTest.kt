package com.example.cursobranasddd.boundaries

import com.example.cursobranasddd.CursoBranasDddApplication
import com.example.cursobranasddd.core.application.GetAccountResponse
import com.example.cursobranasddd.core.application.GetAccountUseCase
import com.example.cursobranasddd.core.application.SignUpCommand
import com.example.cursobranasddd.core.application.SignUpResponse
import com.example.cursobranasddd.core.application.SignUpUseCase
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.UUID

@SpringBootTest(classes = [CursoBranasDddApplication::class])
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
internal class AccountControllerComponentTest {

    private val contextPath = "/account"

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var signUpUseCase: SignUpUseCase

    @MockkBean
    private lateinit var getAccountUseCase: GetAccountUseCase

    @Test
    fun `should return 200 when get account by id`() {
        val id = UUID.randomUUID()
        val expectedResponse = GetAccountResponse(
            accountId = id,
            name = "Joaquim",
            email = "lucas${Math.random()}@example.com",
            cpf = "97456321558",
            carPlate = "AAA9999",
            isPassenger = true,
            isDriver = false
        )
        every { getAccountUseCase.execute(any()) } returns expectedResponse
        mockMvc.perform(MockMvcRequestBuilders.get("$contextPath/$id"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo {
                val response = it.response.contentAsString.parseJsonObject<GetAccountResponse>()
                assertThat(response.accountId).isEqualTo(expectedResponse.accountId)
                assertThat(response.name).isEqualTo(expectedResponse.name)
                assertThat(response.cpf).isEqualTo(expectedResponse.cpf)
                assertThat(response.carPlate).isEqualTo(expectedResponse.carPlate)
                assertThat(response.isDriver).isEqualTo(expectedResponse.isDriver)
                assertThat(response.isPassenger).isEqualTo(expectedResponse.isPassenger)
            }
    }

    @Test
    fun `should return 200 when sign up a new account`() {
        val command = SignUpCommand(
            name = "Joaquim",
            email = "lucas${Math.random()}@example.com",
            cpf = "97456321558",
            carPlate = "AAA9999",
            isPassenger = true,
            isDriver = false
        )
        val expectedId = UUID.randomUUID()

        every { signUpUseCase.execute(command) } returns SignUpResponse(
            accountId = expectedId
        )

        mockMvc.perform(
            MockMvcRequestBuilders.post("$contextPath/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .content(command.toJsonString())
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo {
                val response = it.response.contentAsString.parseJsonObject<SignUpResponse>()
                assertThat(response.accountId).isNotNull()
                assertThat(response.accountId).isEqualTo(expectedId)
            }
    }
}

inline fun <reified T> String.parseJsonObject(): T =
    jacksonObject.readValue<T>(this, object : TypeReference<T>() {})

val jacksonObject = jacksonObjectMapper().apply { registerModule(JavaTimeModule()) }

fun Any.toJsonString(): String = jacksonObject.writeValueAsString(this)
