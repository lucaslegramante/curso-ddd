package com.example.cursobranasddd.infra.gateway

import com.example.cursobranasddd.core.domain.gateway.MailerGateway
import org.springframework.stereotype.Component

@Component
class MailerGateway : MailerGateway {

    override fun send(recipient: String, subject: String, content: String) {
       return println("Sending email to $recipient with subject: $subject and content: $content")
    }
}
