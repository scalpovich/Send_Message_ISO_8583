package com.opw.financemessage.services.Impl;

import com.opw.financemessage.convert.DTO;
import com.opw.financemessage.factory.Processor;
import com.opw.financemessage.mapper.MapperDataElement;
import com.opw.financemessage.models.DataReceive;
import com.opw.financemessage.models.MessageISO;
import com.opw.financemessage.services.MessageBatchService;
import com.opw.financemessage.socket.SocketIO;
import com.opw.financemessage.util.ReadRespondCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ImplMessageBatchService implements MessageBatchService {

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
            if (!socketIO.getConnected() || socketIO.isSocketChange()) {
                System.out.println(socketIO.getConnected());
                return "{\"message\" : \"You have not connected\"}";
            }
            int recentNumb = count++;
            LOGGER.info("Processing request {}", recentNumb);
//            Thread.sleep(5000);
            MessageISO messageISO = dto.dataToMessage(data);
            processor.getInstance(mapperDataElement);

            String messageSend = processor.buildMessage(messageISO);
            LOGGER.info("Message receive " + recentNumb + ": " + messageSend);

//            SocketIO socketIO = new SocketIO();

            socketIO.sendMessage(messageSend);
//            String messageReceiv = socketIO.getMessage();
//            LOGGER.info("Message response " + recentNumb + ": " +  messageReceiv);
            return String.format("{\"message\" : \"Done\"}");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getMessage(int numberTransaction) {
        String messageReceiv;
        for (int i = 0; i < numberTransaction; i++){
            messageReceiv = socketIO.getMessage();
            LOGGER.info("Message response " + i + ": " +  messageReceiv);
        }

        return String.format("{\"message\" : \"DONE\"}");
    }
}
