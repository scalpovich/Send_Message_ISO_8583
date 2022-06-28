package com.opw.financemesage.controllers;

import com.opw.financemesage.models.MessageISO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ControllerBalance {
    @RequestMapping("/balance")
    public String index () {
        return "balance/balance";
    }
    MessageISO sendMessage(){
        return null;
    }
}
