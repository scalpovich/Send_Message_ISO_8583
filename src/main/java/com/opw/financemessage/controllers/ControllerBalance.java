package com.opw.financemessage.controllers;

import com.opw.financemessage.models.DataReceive;
import com.opw.financemessage.services.Impl.MessageBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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
    public CompletableFuture<String> sendMessage(@RequestBody List<DataReceive> data) throws Exception {
       return messageBalanceService.send(data);
    }

//    @PostMapping("/postRawMessage")
//    public String sendMessage(@RequestBody DataReceive data) {
//        return messageBalanceService.sendRawMessage(data.getValue());
//    }

}
