package com.quicksolve.proyecto.service;

import com.quicksolve.proyecto.dto.InvoiceDTO;

public interface InvoiceService {
    InvoiceDTO generateNewInvoice(String email, Long serviceId);
}
