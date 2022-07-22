package com.opw.financemesage.controllers;

import com.opw.financemesage.models.DataReceive;
import com.opw.financemesage.services.Impl.MessagePurchaseService;
import com.opw.financemesage.services.MessageService;
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
