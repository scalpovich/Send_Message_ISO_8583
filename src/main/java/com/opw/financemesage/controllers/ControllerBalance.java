package com.opw.financemesage.controllers;

import com.opw.financemesage.convert.DTO;
import com.opw.financemesage.models.DataReceive;
import com.opw.financemesage.services.Impl.MessageBalanceService;
import com.opw.financemesage.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/balance")
@CrossOrigin
public class ControllerBalance {


    private MessageBalanceService messageBalanceService;
    @Autowired
    public ControllerBalance (MessageBalanceService messageBalanceService){
        this.messageBalanceService = messageBalanceService;
    }

   @PostMapping("/post")
    public String sendMessage(@RequestBody List<DataReceive> data) {
       return messageBalanceService.send(data);
    }

}
