package com.opw.financemesage.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.opw.financemesage.convert.DTO;
import com.opw.financemesage.factory.Processor;
import com.opw.financemesage.mapper.MapperDataElement;
import com.opw.financemesage.models.DataReceive;
import com.opw.financemesage.models.MessageISO;
import com.opw.financemesage.socket.SocketIO;
import com.opw.financemesage.util.ReadRespondCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

@RestController
@RequestMapping("/singletransaction")
@CrossOrigin
public class ControllerSingleTransaction {
    @Autowired
    private MapperDataElement mapperDataElement;

    @Autowired
    private Processor processor;

    @Autowired
    private DTO dto;

    @Autowired
    private SocketIO socketIO;
    @Autowired
    private ReadRespondCode readRespondCode;

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
    public String sendMessage(@RequestBody DataReceive data){
        System.out.println(data.getValue());
        socketIO.sendMessage(data.getValue());
        String messageReceiv = socketIO.getMessage();
        if (messageReceiv == null) {
            System.out.println("k nhận được tin từ Way4");
            return null;
        }
        MessageISO temp = processor.parsMessage(messageReceiv);
        String readResponseCode = temp.getDataElementContent().get(39);
        return String.format("{\"message\" : \"Response code: %s %s\"}", readResponseCode, readRespondCode.read(readResponseCode));
    }
}
