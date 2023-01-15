package com.quicksolve.proyecto.controller;

import com.quicksolve.proyecto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String renderIndex(){
        return "index";
    }

    @GetMapping("/login")
    public String renderLogin(){
        return "view/login";
    }

    @GetMapping("/registro")
    public String renderRegistro(){
        return "view/registro";
    }

    @GetMapping("/user/{id}")
    public void viewUser(@PathVariable Long id){
        System.out.println(userService.getFullUser(id));
    }

    /*

INSERT INTO `user` (`id`, `is_active`, `password`, `type`, `username`, `department_id`, `service_id`) VALUES ('1', b'1', 'qwewqeeqw', '1', 'pepexx222', NULL, NULL);
    INSERT INTO `user_data` (`id`, `created`, `first_surname`, `name`, `second_surname`, `user_id`) VALUES ('1', '2023-01-15 12:26:08.000000', 'juan', 'pepe', 'morales', '1');


    */
}
