package com.opw.financemesage.services.Impl;

import com.opw.financemesage.convert.DTO;
import com.opw.financemesage.factory.Processor;
import com.opw.financemesage.mapper.MapperDataElement;
import com.opw.financemesage.models.DataReceive;
import com.opw.financemesage.models.MessageISO;
import com.opw.financemesage.services.MessageService;
import com.opw.financemesage.socket.SocketIO;
import com.opw.financemesage.util.ReadRespondCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(ImplMessageService.class);
    private int count = 1;

    @Override
    public String sendMessage(List<DataReceive> data) {
        try {
            System.out.println();
            int recentNumb = count++;
            LOGGER.info("Processing request {}", recentNumb);
//            Thread.sleep(5000);
            MessageISO messageISO = dto.dataToMessage(data);
            processor.getInstance(mapperDataElement);

            String messageSend = processor.buildMessage(messageISO);
            System.out.println("Message receive " + recentNumb + ": " +  messageSend);


            socketIO.sendMessage(messageSend);
            String messageReceiv = socketIO.getMessage();

            System.out.println("Message response " + recentNumb + ": " +  messageReceiv);

            if (messageReceiv.charAt(0) == 0) {
                socketIO = new SocketIO();
                return sendMessage(data);
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
