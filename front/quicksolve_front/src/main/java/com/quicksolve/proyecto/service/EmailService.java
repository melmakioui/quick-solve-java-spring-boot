package com.quicksolve.proyecto.service;

public interface EmailService {


     void sendEmail(String to, String incidenceTitle);

     void sendGenericEmail(String to, String html);
}
