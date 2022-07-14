package com.opw.financemesage.services.Impl;

import com.opw.financemesage.convert.DTO;
import com.opw.financemesage.factory.Processor;
import com.opw.financemesage.mapper.MapperDataElement;
import com.opw.financemesage.models.DataReceive;
import com.opw.financemesage.models.MessageISO;
import com.opw.financemesage.services.MessageService;
import com.opw.financemesage.socket.SocketIO;
import com.opw.financemesage.util.ReadRespondCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;


@Service
public class ImplMessageService implements MessageService {

    @Autowired
    private DTO dto;





    @Autowired
    private MapperDataElement mapperDataElement;

    @Autowired
    private Processor processor;

    @Autowired
    private ReadRespondCode readRespondCode;

    @Override
    public String sendMessage(List<DataReceive> data) {
        try {
            MessageISO messageISO = dto.dataToMessage(data);
            processor.getInstance(mapperDataElement);
            String messageSended = processor.buildMessage(messageISO);
            System.out.println(messageSended);
//            System.out.println();
//            socketIO.sendMessage(messageSended);
            String messageReceiv = "";//socketIO.getMessage();
            System.out.println(messageReceiv);
            MessageISO temp = processor.parsMessage(messageReceiv);
            System.out.println(temp.getSecondaryBitMap());
//            for(Integer count: temp.getDataElementContent().keySet()){
//                System.out.println(count + " "+ temp.getDataElementContent().get(count));
//            }
            String readResponseCode = temp.getDataElementContent().get(39);
            System.out.println(readResponseCode);
            return String.format("{\"message\" : \"Response code: %s %s\"}",readResponseCode,readRespondCode.read(readResponseCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DataReceive> getMesssage() {
        MessageISO messageISO = processor.parsMessage("socketIO.getMessage()");
        List<DataReceive> data = dto.messageToData(messageISO);
        return data;
    }

    public String sendRawMessage(String data) {
        try {
            System.out.println("message gui: " + data);
            SocketIO socketIO = new SocketIO();
            socketIO.sendMessage(data);
            String messageReceiv = socketIO.getMessage();
            System.out.println("message nhan: " + messageReceiv);
            processor.getInstance(mapperDataElement);
            MessageISO temp = processor.parsMessage(messageReceiv);
            System.out.println(temp.getSecondaryBitMap());
//            for(Integer count: temp.getDataElementContent().keySet()){
//                System.out.println(count + " "+ temp.getDataElementContent().get(count));
//            }
            String readResponseCode = temp.getDataElementContent().get(39);
            System.out.println(readResponseCode);
            return String.format("{\"message\" : \"Response code: %s %s\"}",readResponseCode,readRespondCode.read(readResponseCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
