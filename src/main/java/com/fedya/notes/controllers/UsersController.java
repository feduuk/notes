package com.fedya.notes.controllers;

import com.fedya.notes.User;
import com.fedya.notes.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UsersController {
    @Autowired
    UserRepository repository;

    @GetMapping("/users")
    public String showUsers(Model model){
        List<User> users = repository.findAll();
        model.addAttribute("users", users);
        return "users";
    }
}
