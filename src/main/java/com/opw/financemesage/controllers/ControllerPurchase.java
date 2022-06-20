package com.opw.financemesage.controllers;

import com.opw.financemesage.models.MessageISO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerPurchase {
    @RequestMapping("")
    MessageISO sendMessage(){
        return null;
    }
}
