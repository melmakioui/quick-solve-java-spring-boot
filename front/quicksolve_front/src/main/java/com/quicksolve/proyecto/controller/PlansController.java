package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.entity.type.UserType;
import com.quicksolve.proyecto.service.InvoiceService;
import com.quicksolve.proyecto.service.ServiceService;
import com.quicksolve.proyecto.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@SessionAttributes({"userlogin"})
public class PlansController {

    @Autowired
    ServiceService serviceServ;

    @Autowired
    UserService userService;

    @Autowired
    InvoiceService invService;

    @GetMapping("/planes")
    public String renderPlans(Model model){
        if(((FullUserDTO) model.getAttribute("userlogin")).getType() != UserType.USER) return "redirect:/error";
        model.addAttribute("planes", serviceServ.showAll());
        return "view/planes";
    }

    @PostMapping("/modificar/plan")
    public @ResponseBody void modifyPlan(@RequestBody String planId, Model model, HttpServletResponse response) throws IOException {
        planId = planId.replace("plan=", "");
        long idPlan = Long.parseLong(planId);
        if (idPlan <= serviceServ.getLastService() && idPlan >= 0){
            FullUserDTO newUser = userService.updateService(((FullUserDTO) model.getAttribute("userlogin")).getEmail(), idPlan);
            if (idPlan != 0){
                newUser.addInvoice(invService.generateNewInvoice(newUser.getEmail(), idPlan));
            }
            model.addAttribute("userlogin", newUser);
        }
        response.sendRedirect("/planes");
    }
}
