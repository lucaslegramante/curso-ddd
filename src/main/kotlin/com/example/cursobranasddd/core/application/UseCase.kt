package com.example.cursobranasddd.core.application

interface UseCase<IN, OUT> {
    fun execute(input: IN): OUT
}

interface UnitUseCase<IN> {
    fun execute(input: IN)
}

interface UseCaseWithoutCommand {
    fun execute()
}

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class UseCaseAnnotation
