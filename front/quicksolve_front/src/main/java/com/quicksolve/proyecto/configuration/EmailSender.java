package com.quicksolve.proyecto.configuration;

import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.message.Message;
import com.mailgun.model.message.MessageResponse;
import lombok.Value;
import org.springframework.core.env.Environment;

public class EmailSender {

    private static Environment env;
    public static MessageResponse sendEmail(String to, String subject, String body) {
        MailgunMessagesApi mailgunMessagesApi = MailgunClient.config(env.getProperty("mailgun.apiKey"))
                .createApi(MailgunMessagesApi.class);

        Message message = Message.builder()
                .from(env.getProperty("mailgun.from"))
                .to(to)
                .subject(subject)
                .text(body)
                .build();

        return mailgunMessagesApi.sendMessage(env.getProperty("mailgun.domain"), message);
    }
}
