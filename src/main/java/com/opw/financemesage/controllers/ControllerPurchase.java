package com.opw.financemesage.controllers;

import com.opw.financemesage.models.DataReceive;
import com.opw.financemesage.models.MessageISO;
import com.opw.financemesage.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/purchase")
@CrossOrigin
public class ControllerPurchase {
    @Autowired
    private MessageService ms;
    @PostMapping("/post")
    public String add(@RequestBody List<DataReceive> data){
//        for(int i=0; i< data.size(); i++){
//            System.out.println(data.get(i).getId() + " " + data.get(i).getValue());
//        }
        ms.buildMessage(data);
        return "New message added";
    }

    @GetMapping("/get")
    public List<DataReceive> list(){
        return ms.getMessage();
    }

}
