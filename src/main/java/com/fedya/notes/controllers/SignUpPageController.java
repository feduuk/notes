package com.fedya.notes.controllers;

import com.fedya.notes.User;
import com.fedya.notes.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SignUpPageController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository repository;
    @GetMapping("/signup")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/process_register")
    public String processRegister(User user, RedirectAttributes attributes){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repository.save(user);
        return "redirect:/";
    }

}
