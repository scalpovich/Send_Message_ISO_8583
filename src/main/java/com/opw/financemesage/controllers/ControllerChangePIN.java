package com.opw.financemesage.controllers;

import com.opw.financemesage.models.DataReceive;
import com.opw.financemesage.services.Impl.MessageChangePINService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/changePIN")
@CrossOrigin
public class ControllerChangePIN {
    private MessageChangePINService messageChangePINService;

    @Autowired
    public ControllerChangePIN (MessageChangePINService messageChangePINService){
        this.messageChangePINService = messageChangePINService;
    }

    @PostMapping("/post")
    public CompletableFuture<String> sendMessage(@RequestBody List<DataReceive> data){
        return messageChangePINService.send(data);
    }
}
