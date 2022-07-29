package com.opw.financemessage.controllers;

import com.opw.financemessage.models.DataReceive;
import com.opw.financemessage.services.Impl.ImplMessageService;
import com.opw.financemessage.services.Impl.MessageBalanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/balance")
@CrossOrigin
public class ControllerBalance {

    private MessageBalanceService messageBalanceService;
//    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerBalance.class);
    @Autowired
    public ControllerBalance (MessageBalanceService messageBalanceService){
        this.messageBalanceService = messageBalanceService;
    }

   @PostMapping("/post")
    public CompletableFuture<String> sendMessage(@RequestBody List<DataReceive> data) throws Exception {
//       LOGGER.info("Processing request hhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
       return messageBalanceService.send(data);
    }

//    @PostMapping("/postRawMessage")
//    public String sendMessage(@RequestBody DataReceive data) {
//        return messageBalanceService.sendRawMessage(data.getValue());
//    }
}
