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
    private SocketIO socketIO;


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
            socketIO.sendMessage(messageSended);
            String messageReceiv = socketIO.getMessage();
            System.out.println(messageReceiv);
            String readResponseCode = processor.parsMessage(messageReceiv).getDataElementContent().get(39);
            return String.format("{\"message\" : \"%s\"}",readRespondCode.read(readResponseCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DataReceive> getMesssage() {
        MessageISO messageISO = processor.parsMessage(socketIO.getMessage());
        List<DataReceive> data = dto.messageToData(messageISO);
        return data;
    }
}
