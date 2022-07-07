package com.opw.financemesage.controllers;

import com.opw.financemesage.convert.DTO;
import com.opw.financemesage.models.DataReceive;
import com.opw.financemesage.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/balance")
@CrossOrigin
public class ControllerBalance {

    @Autowired
    private MessageService messageService;
    @Autowired
    private DTO dto;

   @PostMapping("/post")
    public String sendMessage(@RequestBody List<DataReceive> data) {
       return messageService.sendMessage(data);
    }

    @GetMapping("/get")
    public List<DataReceive> list(){
       return messageService.getMesssage();
    }
}
