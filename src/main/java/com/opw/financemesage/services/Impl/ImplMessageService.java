package com.opw.financemesage.services.Impl;

import com.opw.financemesage.factory.Processor;
import com.opw.financemesage.mapper.MapperDataElement;
import com.opw.financemesage.models.MessageISO;
import com.opw.financemesage.services.MessageService;
import com.opw.financemesage.socket.SocketIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ImplMessageService implements MessageService {

    @Autowired
    private SocketIO socketIO;

    @Autowired
    private MapperDataElement mapperDataElement;

    @Autowired
    private Processor processor;

    @Override
    public MessageISO sendMessage(MessageISO message) {
        try {
            processor.getInstance(mapperDataElement);
            String mesageISO = processor.buildMessage(message);
            socketIO.sendMessage(mesageISO);

            return processor.parsMessage(socketIO.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
