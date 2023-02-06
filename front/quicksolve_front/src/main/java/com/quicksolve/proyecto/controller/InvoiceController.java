package com.quicksolve.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"userlogin"})
public class InvoiceController {

    @GetMapping("/invoices")
    public String showInvoices(){
        return "view/invoices";
    }
}
