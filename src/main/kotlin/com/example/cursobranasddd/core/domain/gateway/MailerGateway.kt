package com.example.cursobranasddd.core.domain.gateway

interface MailerGateway {
    fun send(recipient: String, subject: String, content: String)
}