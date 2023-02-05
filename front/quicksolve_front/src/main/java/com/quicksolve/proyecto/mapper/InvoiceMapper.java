package com.quicksolve.proyecto.mapper;

import com.quicksolve.proyecto.dto.InvoiceDTO;
import com.quicksolve.proyecto.entity.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InvoiceMapper {
    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    @Mapping(source = "invoiceLines", target = "invoiceLines")
    InvoiceDTO invoiceToDTO(Invoice inv);
}
