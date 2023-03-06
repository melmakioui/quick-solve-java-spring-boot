package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.dto.UserDataDTO;
import com.quicksolve.proyecto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@SessionAttributes({"userlogin"})
public class CuentaController {

    @Autowired
    private UserService userService;

    @GetMapping("/cuenta")
    public String showAccount(){
        return "view/account";
    }

    @PostMapping("/cuenta")
    public String updateAccount(
            @RequestParam("username") String username,
            @RequestParam("name") String name,
            @RequestParam("firstSurname") String firstSurname,
            @RequestParam(value = "secondSurname", required = false) String secondSurname,
            Model model,
            RedirectAttributes redirectAttributes
    ){
        String email = ((FullUserDTO) model.getAttribute("userlogin")).getEmail();
        FullUserDTO newUser = userService.updateUser(new FullUserDTO (email, username, new UserDataDTO(name, firstSurname, secondSurname)));
        model.addAttribute("userlogin", newUser);
        redirectAttributes.addFlashAttribute("updatedUser", true);
        return "redirect:/cuenta";
    }
}
