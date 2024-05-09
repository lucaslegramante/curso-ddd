package com.example.cursobranasddd.core.domain.entity

import com.example.cursobranasddd.core.domain.InstantUtils
import com.example.cursobranasddd.core.domain.entity.entities.AggregateRoot
import com.example.cursobranasddd.core.domain.entity.entities.Identifier
import com.example.cursobranasddd.core.domain.vo.CarPlate
import com.example.cursobranasddd.core.domain.vo.Cpf
import com.example.cursobranasddd.core.domain.vo.Email
import com.example.cursobranasddd.core.domain.vo.Name
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.util.UUID

class Account private constructor(
    override val id: Identifier,
    private val name: Name,
    private val email: Email,
    private val cpf: Cpf,
    private val carPlate: CarPlate?,
    private val isPassanger: Boolean,
    private val isDriver: Boolean,

    val createdAt: Instant,
    var updatedAt: Instant
) : AggregateRoot<Identifier>(id) {

    companion object {
        fun new(
            name: Name,
            email: Email,
            cpf: Cpf,
            carPlate: CarPlate?,
            isPassanger: Boolean,
            isDriver: Boolean
        ) = Pair(Identifier.unique(), InstantUtils.now()).let { (id, now) ->
            Account(
                id = id,
                name = name,
                email = email,
                cpf = cpf,
                carPlate = carPlate,
                isPassanger = isPassanger,
                isDriver = isDriver,
                createdAt = now,
                updatedAt = now
            )
        }

        // na funcao new, deveria receber string e a propria account converter para o value object?

        fun from(
            id: UUID,
            platformId: String,
            creditCertificateNumber: String,
            financedAmount: BigDecimal,
            issueDate: LocalDate,
            productType: String,
            indexType: String?,
            gracePeriod: Int,
            creditorPlatformId: String,
            loanGroupId: UUID?,
            protocoledAt: LocalDate?,
            status: LoanStatus,
            createdAt: Instant,
            updatedAt: Instant,
            withAssignmentIntention: Boolean,
            underwriterPlatformId: String,
            tacAmount: BigDecimal?,
            restructurings: MutableList<Restructuring>
        ): Loan = Loan(
            id = Identifier.from(id),
            platformId = platformId,
            creditCertificateNumber = creditCertificateNumber,
            financedAmount = financedAmount,
            issueDate = issueDate,
            productType = productType,
            indexType = indexType,
            gracePeriod = gracePeriod,
            creditorPlatformId = creditorPlatformId,
            loanGroupId = loanGroupId,
            protocoledAt = protocoledAt,
            status = status,
            createdAt = createdAt,
            updatedAt = updatedAt,
            withAssignmentIntention = withAssignmentIntention,
            underwriterPlatformId = underwriterPlatformId,
            tacAmount = tacAmount,
            restructurings = restructurings
        )

        private fun gracePeriod(firstInstallmentDueDate: LocalDate, issueDate: LocalDate): Int =
            DateUtils.diffInDaysBetween(issueDate, firstInstallmentDueDate)
    }