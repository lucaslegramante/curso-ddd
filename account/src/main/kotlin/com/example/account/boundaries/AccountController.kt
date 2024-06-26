package com.example.account.boundaries

import com.example.account.core.application.AccountNotFoundException
import com.example.account.core.application.GetAccountCommand
import com.example.account.core.application.GetAccountData
import com.example.account.core.application.GetAccountUseCase
import com.example.account.core.application.SignUpCommand
import com.example.account.core.application.SignUpResponse
import com.example.account.core.application.SignUpUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/account")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class AccountController(
    private val signUpUseCase: SignUpUseCase,
    private val getAccountUseCase: GetAccountUseCase
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody command: SignUpCommand): ResponseEntity<SignUpResponse> {
        val response = signUpUseCase.execute(command)
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

//    @GetMapping("/{id}")
//    fun getAccount(@PathVariable(name = "id") id: UUID): ResponseEntity<Any> {
//        val response = getAccountUseCase.execute(GetAccountCommand(id))
//        return if (response.hasErrors) {
//            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.errors)
//        } else {
//            ResponseEntity.status(HttpStatus.OK).body(response.data)
//        }
//    }

    @GetMapping("/{id}")
    fun getAccountV2(@PathVariable(name = "id") id: UUID): ResponseEntity<*> {
        val response = getAccountUseCase.execute(GetAccountCommand(id))
        return if (response.isRight) {
            val response2 = response as AccountNotFoundException
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response2.message)
        } else {
            val response3 = response
            ResponseEntity.status(HttpStatus.OK).body(response3)
        }
    }
}
