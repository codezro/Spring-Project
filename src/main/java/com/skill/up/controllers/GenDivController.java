package com.skill.up.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jpmc on 11/16/2016.
 */
@Controller
public class GenDivController {

    @RequestMapping("/gendivs")
    public String redirect() {
        return "redirect:/gendiv?color1=red&color2=green&color3=blue";
    }

    @RequestMapping(value = "/gendiv", method = RequestMethod.GET)
    public String hello(){return "practice/gendiv";}
}
