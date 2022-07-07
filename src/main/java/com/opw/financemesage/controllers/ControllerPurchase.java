package com.opw.financemesage.controllers;

import com.opw.financemesage.models.DataReceive;
import com.opw.financemesage.models.MessageISO;
import com.opw.financemesage.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.apache.coyote.http11.Constants.a;


@RestController
@RequestMapping("/purchase")
@CrossOrigin
public class ControllerPurchase {
    @Autowired
    private MessageService ms;
    @PostMapping("/post")
    public String add(@RequestBody List<DataReceive> datas){
        ms.sendMessage(datas);
        return "New message added";
    }

    @GetMapping("/get")
    public List<DataReceive> list(){
        return ms.getMesssage();
    }
}
