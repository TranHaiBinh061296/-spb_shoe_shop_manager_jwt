package com.codegym.controller;

import com.codegym.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/operator")
public class OperatorController {
    @Autowired
    private AppUtils appUtils;



    @GetMapping
    public ModelAndView showDashboardPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("web/home");

        String username = appUtils.getPrincipalUsername();

        modelAndView.addObject("username", username);

        return modelAndView;
    }
    @GetMapping("/home")
    public ModelAndView showWebPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("web/index");

        return modelAndView;
    }
    @GetMapping("/products")
    public ModelAndView showProductPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product/list");

        String username = appUtils.getPrincipalUsername();

        modelAndView.addObject("username", username);

        return modelAndView;
    }
    @GetMapping("/customers")
    public ModelAndView showCustomerPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/list");

        String username = appUtils.getPrincipalUsername();

        modelAndView.addObject("username", username);

        return modelAndView;
    }


}
