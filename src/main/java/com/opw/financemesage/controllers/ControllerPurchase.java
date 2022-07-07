package com.opw.financemesage.controllers;

import com.opw.financemesage.models.DataReceive;
import com.opw.financemesage.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
@CrossOrigin
public class ControllerPurchase {
    @Autowired
    private MessageService ms;
    @PostMapping("/post")
    public String add(@RequestBody List<DataReceive> datas){

        return ms.sendMessage(datas);
    }

    @GetMapping("/get")
    public List<DataReceive> list(){
        return ms.getMesssage();
    }
}
