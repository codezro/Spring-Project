package com.skill.up.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jpmc on 11/16/2016.
 */
@Controller
public class NewsController {
    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String hello(){return "news/sports";}
}
