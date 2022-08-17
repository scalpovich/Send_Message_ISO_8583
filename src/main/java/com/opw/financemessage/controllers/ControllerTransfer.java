package com.opw.financemessage.controllers;

import com.opw.financemessage.models.DataReceive;
import com.opw.financemessage.services.Impl.MessageTransferService;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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

    @GetMapping(path = "/get/{ID}")
    public CompletableFuture<String> getAllCoursesForStudent(
            @PathVariable("ID") String field63) {
        long startTime = System.nanoTime();
        return messageTransferService.getMessageByField63(field63, startTime);
    }

    @GetMapping("/getfield")
    public JSONArray getField() throws Exception {
        JSONParser jsonParser = new JSONParser();

        File file = new File("ControllerBalance.java");
//        System.out.println(file.getAbsoluteFile().getParent());

        FileReader reader = new FileReader(file.getAbsoluteFile().getParent() + "\\src\\main\\resources\\transactionField\\TransferField.json");
        Object obj = jsonParser.parse(reader);

        JSONArray fieldList = (JSONArray) obj;
//        System.out.println(fieldList);
        return fieldList;
    }

    @PostMapping("/postfield")
    public void postField(@RequestBody String fieldList) throws Exception {


//        fieldList = fieldList.substring(1, fieldList.length()-1).replace("\\", "");
        System.out.println(fieldList);

        File file = new File("ControllerBalance.java");
        FileWriter writer = new FileWriter(file.getAbsoluteFile().getParent() + "\\src\\main\\resources\\transactionField\\TransferField.json");
        writer.write(fieldList);
        writer.flush();
    }
}
