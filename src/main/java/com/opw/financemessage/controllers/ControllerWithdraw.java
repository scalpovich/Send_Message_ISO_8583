package com.opw.financemessage.controllers;

import com.opw.financemessage.models.DataReceive;
import com.opw.financemessage.services.Impl.MessageWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/withdraw")
@CrossOrigin
public class ControllerWithdraw {

    private MessageWithdrawService messageWithdrawService;

    @Autowired
    public ControllerWithdraw(MessageWithdrawService messageWithdrawService) {
        this.messageWithdrawService = messageWithdrawService;
    }

    @PostMapping("/post")
    public CompletableFuture<String> sendMessage(@RequestBody List<DataReceive> data) {
        return messageWithdrawService.send(data);
    }
}
