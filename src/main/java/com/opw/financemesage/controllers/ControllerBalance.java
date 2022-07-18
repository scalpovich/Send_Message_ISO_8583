package com.opw.financemesage.controllers;

import com.opw.financemesage.models.DataReceive;
import com.opw.financemesage.services.Impl.MessageBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/balance")
@CrossOrigin
public class ControllerBalance {
    @Autowired
    private MessageBalanceService messageBalanceService;


   @PostMapping("/post")
    public CompletableFuture<String> sendMessage(@RequestBody List<DataReceive> data) throws Exception {
       return messageBalanceService.send(data);
    }

//    @PostMapping("/postRawMessage")
//    public String sendMessage(@RequestBody DataReceive data) {
//        return messageBalanceService.sendRawMessage(data.getValue());
//    }

}
