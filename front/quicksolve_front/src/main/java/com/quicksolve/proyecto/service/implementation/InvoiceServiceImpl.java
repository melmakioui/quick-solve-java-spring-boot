package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.dto.InvoiceDTO;
import com.quicksolve.proyecto.dto.ServiceDTO;
import com.quicksolve.proyecto.entity.Invoice;
import com.quicksolve.proyecto.entity.InvoiceLine;
import com.quicksolve.proyecto.entity.User;
import com.quicksolve.proyecto.entity.UserData;
import com.quicksolve.proyecto.mapper.InvoiceMapper;
import com.quicksolve.proyecto.repository.*;
import com.quicksolve.proyecto.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    UserRepository userRepo;
    @Autowired
    UserDataRepository userDataRepo;
    @Autowired
    ServiceRepository serviceRepo;
    @Autowired
    InvoiceRepository invoiceRepo;
    @Autowired
    InvoiceLineRepository invoiceLineRepo;

    @Override
    public InvoiceDTO generateNewInvoice(String email, Long serviceId){
        User usr = userRepo.findByEmail(email);
        UserData userData = userDataRepo.findByUserId(usr.getId());
        com.quicksolve.proyecto.entity.Service service = serviceRepo.getReferenceById(serviceId);
        Invoice inv = new Invoice(LocalDateTime.now(), userData.getName(), userData.getFirstSurname(), userData.getSecondSurname(), usr);
        InvoiceLine invLine = new InvoiceLine(service.getName(), service.getPrice(), service.getTax(), inv);

        invoiceRepo.save(inv);
        invoiceLineRepo.save(invLine);

        return InvoiceMapper.INSTANCE.invoiceToDTO(inv);
    }
}
