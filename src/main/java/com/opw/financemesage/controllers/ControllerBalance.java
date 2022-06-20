package com.opw.financemesage.controllers;

import com.opw.financemesage.models.MessageISO;
import com.opw.financemesage.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerBalance {
    @Autowired
    MessageService messageService;

    MessageISO sendMessage(){
        return null;
    }
}
