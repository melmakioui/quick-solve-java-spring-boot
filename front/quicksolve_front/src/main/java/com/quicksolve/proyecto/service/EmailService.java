package com.quicksolve.proyecto.service;

public interface EmailService {


     void sendEmail(String to, String incidenceTitle);

     void sendEmailVerificationAccount(String to,String html);
}
