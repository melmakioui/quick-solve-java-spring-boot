package com.quicksolve.proyecto.service.implementation;

import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.message.Message;
import com.quicksolve.proyecto.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${mailgun.api.key}")
    private String API_KEY;
    @Value("${mailgun.domain}")
    private String DOMAIN;
    @Value("${mailgun.url}")
    private String URL;
    @Value("${mailgun.from}")
    private String FROM;

    @Override
    public void sendEmail(String to, String incidenceTitle) {

        try {
            MailgunMessagesApi mailgunMessagesApi = MailgunClient.config(URL, API_KEY)
                    .createApi(MailgunMessagesApi.class);

            //TODO enviar un html con el link a la incidencia
            Message message = Message.builder()
                    .from(FROM)
                    .to(to)
                    .subject("No Reply")
                    .html("<html><body><p>Ha habido un cambio en tu incidencia : <strong>" + incidenceTitle + "</strong></p></body></html>")
                    .build();
            mailgunMessagesApi.sendMessage(DOMAIN, message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
