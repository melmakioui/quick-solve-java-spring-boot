package com.quicksolve.proyecto.mapper;

import com.quicksolve.proyecto.dto.InvoiceLineDTO;
import com.quicksolve.proyecto.entity.InvoiceLine;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InvoiceLineMapper {
    InvoiceLineMapper INSTANCE = Mappers.getMapper(InvoiceLineMapper.class);

    InvoiceLineDTO invoiceLineToDTO(InvoiceLine inv);
}
