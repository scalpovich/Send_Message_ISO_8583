package com.opw.financemessage.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.opw.financemessage.convert.DTO;
import com.opw.financemessage.factory.Processor;
import com.opw.financemessage.mapper.MapperDataElement;
import com.opw.financemessage.models.DataReceive;
import com.opw.financemessage.models.MessageISO;
import com.opw.financemessage.services.Impl.MessageSingleTransaction;
import com.opw.financemessage.socket.SocketIO;
import com.opw.financemessage.util.ReadRespondCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/singletransaction")
@CrossOrigin
public class ControllerSingleTransaction {
    private MessageSingleTransaction messageSingleTransaction;
    @Autowired
    private MapperDataElement mapperDataElement;
    @Autowired
    private Processor processor;
    @Autowired
    private DTO dto;
    @Autowired
    public ControllerSingleTransaction(MessageSingleTransaction messageSingleTransaction){
        this.messageSingleTransaction = messageSingleTransaction;
    }


    @PostMapping("/parsemessage")
    public String parseMessage(@RequestBody DataReceive rawMesaage){
        processor.getInstance(mapperDataElement);
        MessageISO messageISO = processor.parsMessage(rawMesaage.getValue());
        List <DataReceive> resp = new ArrayList<>();
        resp.add(new DataReceive(1,messageISO.getMti()));
        TreeMap<Integer, String> tm = new TreeMap<Integer, String>(messageISO.getDataElementContent());
        Iterator itr = tm.keySet().iterator();
        while (itr.hasNext()) {
            int key = (int) itr.next();
            resp.add(new DataReceive(key,messageISO.getDataElementContent().get(key)));
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String jsonResp = "";
        try {
            jsonResp = objectMapper.writeValueAsString(resp);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonResp;
    }
    @PostMapping("/buildmessage")
    public String buildMessage(@RequestBody List<DataReceive> listElement){
        MessageISO messageISO = dto.dataToMessage(listElement);
        processor.getInstance(mapperDataElement);
        String messageparsed = processor.buildMessage(messageISO);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String jsonResp = "";
        try {
            jsonResp = objectMapper.writeValueAsString(new DataReceive(0,messageparsed));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonResp;
    }

    @PostMapping("/send")
    public CompletableFuture<String> sendMessage(@RequestBody List<DataReceive> listElement) throws Exception {
        return messageSingleTransaction.send(listElement);
    }
}