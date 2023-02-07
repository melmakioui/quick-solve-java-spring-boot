package com.quicksolve.proyecto.repository;

import com.quicksolve.proyecto.entity.InvoiceLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceLineRepository extends JpaRepository<InvoiceLine, Long> {
}
