package com.quicksolve.proyecto.repository;

import com.quicksolve.proyecto.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
