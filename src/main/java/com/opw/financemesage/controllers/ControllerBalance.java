package com.opw.financemesage.controllers;

import com.opw.financemesage.models.MessageISO;
import com.opw.financemesage.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/balance")
public class ControllerBalance {

    @Autowired
    private MessageService messageService;
   @PostMapping("/post")
    public MessageISO sendMessage(@RequestBody MessageISO messageISO) {
        return messageService.sendMessage(messageISO);
    }
}
