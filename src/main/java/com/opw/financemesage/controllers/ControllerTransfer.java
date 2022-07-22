package com.opw.financemesage.controllers;

import com.opw.financemesage.models.DataReceive;
import com.opw.financemesage.services.Impl.MessageTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/transfer")
@CrossOrigin
public class ControllerTransfer {

    private MessageTransferService messageTransferService;

    @Autowired
    public ControllerTransfer(MessageTransferService messageTransferService){
        this.messageTransferService = messageTransferService;
    }

    @PostMapping("/post")
    public CompletableFuture<String> sendMessage(@RequestBody List<DataReceive> data){
        return messageTransferService.send(data);
    }
}
