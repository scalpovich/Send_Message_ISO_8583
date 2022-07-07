package com.opw.financemesage.services.Impl;

import com.opw.financemesage.convert.DTO;
import com.opw.financemesage.factory.Processor;
import com.opw.financemesage.mapper.MapperDataElement;
import com.opw.financemesage.models.DataReceive;
import com.opw.financemesage.models.MessageISO;
import com.opw.financemesage.services.MessageService;
import com.opw.financemesage.socket.SocketIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


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

    @Override
    public List<DataReceive> sendMessage(List<DataReceive> data) {
        try {
            MessageISO messageISO = dto.dataToMessage(data);
            processor.getInstance(mapperDataElement);
            String mesageISO = processor.buildMessage(messageISO);
            socketIO.sendMessage(mesageISO);
            String message = socketIO.getMessage();
            System.out.println(message);
            MessageISO messageISO1 = processor.parsMessage(message);
            List<DataReceive> res = dto.messageToData(messageISO1);
            return res;
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
