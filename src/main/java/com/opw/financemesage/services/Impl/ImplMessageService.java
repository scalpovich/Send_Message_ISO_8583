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

@Service
public class ImplMessageService implements MessageService {

    @Autowired
    private SocketIO socketIO;
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

            socketIO.sendMessage(messageSended);
            String messageReceiv = socketIO.getMessage();
            if (messageReceiv == null) {
                System.out.println("k nhận được tin từ Way4");
                return null;
            }

            MessageISO temp = processor.parsMessage(messageReceiv);
            String readResponseCode = temp.getDataElementContent().get(39);
            return String.format("{\"message\" : \"Response code: %s %s\"}", readResponseCode, readRespondCode.read(readResponseCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
