package com.opw.financemessage.controllers;

import com.opw.financemessage.models.DataReceive;
import com.opw.financemessage.services.Impl.ImplMessageService;
import com.opw.financemessage.services.Impl.MessageBalanceService;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/statement")
@CrossOrigin
public class ControllerStatement {

    private MessageBalanceService messageBalanceService;
    //    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerBalance.class);
    @Autowired
    public ControllerStatement (MessageBalanceService messageBalanceService){
        this.messageBalanceService = messageBalanceService;
    }

    @PostMapping("/post")
    public CompletableFuture<String> sendMessage(@RequestBody List<DataReceive> data) throws Exception {
        String f63 = messageBalanceService.send(data).get();

        return messageBalanceService.getMessageByField63(f63, System.nanoTime());
    }

    @GetMapping(path = "/get/{ID}")
    public CompletableFuture<String> getAllCoursesForStudent(
            @PathVariable("ID") String field63) {
        long startTime = System.nanoTime();
        return messageBalanceService.getMessageByField63(field63, startTime);
    }

    @GetMapping("/getfield")
    public JSONArray getField() throws Exception {
        JSONParser jsonParser = new JSONParser();
//        System.out.println(file.getAbsoluteFile().getParent());
        FileReader reader = new FileReader("src/main/resources/transactionField/StatementField.json");
        Object obj = jsonParser.parse(reader);
        JSONArray fieldList = (JSONArray) obj;
//        System.out.println(fieldList);
        return fieldList;
    }

    @PostMapping("/postfield")
    public void postField(@RequestBody String fieldList) throws Exception {
//        fieldList = fieldList.substring(1, fieldList.length()-1).replace("\\", "");
        System.out.println(fieldList);

        FileWriter writer = new FileWriter("src/main/resources/transactionField/StatementField.json");
        writer.write(fieldList);
        writer.flush();
    }

}
