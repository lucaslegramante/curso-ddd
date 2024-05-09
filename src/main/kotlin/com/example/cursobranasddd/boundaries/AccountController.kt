package com.example.cursobranasddd.boundaries

import com.example.cursobranasddd.core.application.GetAccountCommand
import com.example.cursobranasddd.core.application.GetAccountResponse
import com.example.cursobranasddd.core.application.GetAccountUseCase
import com.example.cursobranasddd.core.application.SignUpCommand
import com.example.cursobranasddd.core.application.SignUpResponse
import com.example.cursobranasddd.core.application.SignUpUseCase
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

    @GetMapping("/{id}")
    fun getAccount(@PathVariable(name = "id") id: UUID): ResponseEntity<GetAccountResponse> {
        val response = getAccountUseCase.execute(GetAccountCommand(id))
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}