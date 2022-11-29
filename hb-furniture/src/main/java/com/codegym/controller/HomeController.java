package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class HomeController {

    @GetMapping("/")
    public String showIndex() {
        return "redirect:/operator";
    }

    @GetMapping("/login")
    public ModelAndView getLogin() {

        return new ModelAndView("login");
    }


}
