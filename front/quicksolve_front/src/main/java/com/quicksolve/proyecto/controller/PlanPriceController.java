package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanPriceController {

    @Autowired
    private ServiceService serviceServ;

    @PostMapping("/planes/precio")
    public @ResponseBody double getPrecioPlan(@RequestBody String id){
        return serviceServ.getServicePrice(Long.parseLong(id));
    }
}
