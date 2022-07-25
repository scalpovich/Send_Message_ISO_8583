package com.opw.financemessage.controllers;

import com.opw.financemessage.models.DataReceive;
import com.opw.financemessage.services.Impl.MessagePurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/purchase")
@CrossOrigin
public class ControllerPurchase {

    private MessagePurchaseService messagePurchaseService;
    @Autowired
    public ControllerPurchase(MessagePurchaseService messagePurchaseService){
        this.messagePurchaseService = messagePurchaseService;
    }
    @PostMapping("/post")
    public CompletableFuture<String> sendMessage(@RequestBody List<DataReceive> data){
        return messagePurchaseService.send(data);
    }


}
