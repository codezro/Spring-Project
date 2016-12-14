package com.skill.up.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jpmc on 11/16/2016.
 */
@Controller
public class AboutController {

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String hello(){return "about/info";}

    @RequestMapping("/info")
    public String redirect() {
        return "redirect:/query?q=Thymeleaf+Is+Great!";
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String thankyou(){return "info/thankyou";}

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(){return "main/thankyou";}

}
