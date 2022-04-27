package com.fedya.notes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@ControllerAdvice
public class HomePageController {
    @GetMapping("/")
    public String showHomePage(){
        return "index";
    }
}
