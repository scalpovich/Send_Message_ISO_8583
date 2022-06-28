package com.opw.financemesage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerHome {
    @RequestMapping("/")
    public String home () {
        return "home";
    }
}
