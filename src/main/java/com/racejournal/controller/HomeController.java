package com.racejournal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by alaplante on 4/17/16.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/home")
    public String index() {
        return "home";
    }
}
