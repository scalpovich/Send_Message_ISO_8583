package com.opw.financemessage.services.Impl;

import com.opw.financemessage.convert.DTO;
import com.opw.financemessage.entity.MessageEntity;
import com.opw.financemessage.entity.TransLog;
import com.opw.financemessage.factory.Processor;
import com.opw.financemessage.factory.SystemParameters;
import com.opw.financemessage.mapper.MapperDataElement;
import com.opw.financemessage.models.DataReceive;
import com.opw.financemessage.models.MessageISO;
import com.opw.financemessage.repository.OnlineLogRepository;
import com.opw.financemessage.repository.TransLogRepository;
import com.opw.financemessage.services.MessageService;
import com.opw.financemessage.socket.ManageSocket;
import com.opw.financemessage.socket.SocketIO;
import com.opw.financemessage.util.ReadRespondCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@EnableScheduling
public class ImplMessageService implements MessageService {
    @Autowired
    ManageSocket manageSocket;
    @Autowired
    private DTO dto;

    //    @Qualifier("testConfigMapper")
    @Autowired
    private MapperDataElement mapperDataElement;

    @Autowired
    private Processor processor;

    @Autowired
    private ReadRespondCode readRespondCode;

    @Autowired
    private OnlineLogRepository onlineLogRepository;

    @Autowired
    private TransLogRepository transLogRepository;

    @Autowired
    private SystemParameters parameters;

    @Autowired
    private MessageEntity messageEntity;

    private static final Logger LOGGER = LoggerFactory.getLogger(ImplMessageService.class);
    private int count = 1;
    private static Map<String, String> rcStorage = new HashMap<String, String>();
    private static Map<String, Long> idStorage = new HashMap<String, Long>();

    @Override
    public String sendMessage(List<DataReceive> data, String filed63) {
        try {
//            if (!socketIO.getConnected() /*|| socketIO.isSocketChange()*/){
//                System.out.println(socketIO.getConnected());
//                return "{\"message\" : \"You have not connected\"}";
//            }
            int recentNumb = count++;
            System.out.println();
            LOGGER.info("Processing request {}", filed63);


            Map<Integer, String> mapData = new HashMap<Integer, String>();
            for (int i = 0; i < data.size(); i++) {
                mapData.put(data.get(i).getId(), data.get(i).getValue());
            }
            if (mapData.containsKey(4)) {
                data.add(new DataReceive(5, mapData.get(4)));
                mapData.put(5, mapData.get(4));
            }
            if (mapData.containsKey(49)) {
                data.add(new DataReceive(50, mapData.get(49)));
                mapData.put(50, mapData.get(49));
            }

//            SocketIO socketIO = manageSocket.getSocketByID(mapData.get(2).substring(0, 6));
            SocketIO socketIO = manageSocket.getSocketByID("970409");

            if (socketIO == null) {
                LOGGER.info("Not contain socket");
            }

            long id = transLogRepository.addTransLog(mapData);
            idStorage.put(filed63, id);

//            Thread.sleep(5000);
            MessageISO messageISO = dto.dataToMessage(data);
            processor.getInstance(mapperDataElement);

            String messageSend = processor.buildMessage(messageISO);

            onlineLogRepository.addOnlineLog(filed63, true, mapData.get(0), messageSend, null);
//            LOGGER.info("Message receive " + recentNumb + ": " +  messageSend);
            socketIO.sendMessage(messageSend);

            return String.format(filed63);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String getMessage(String field63, long startTime) {
        LOGGER.info(field63);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        long timeOut = (long) parameters.getTimeoutMessage() * (long) Math.pow(10, 9);
        while (!rcStorage.containsKey(field63)) {
            if (System.nanoTime() - startTime > timeOut)
                return String.format("{\"message\" : \"Timeout r bro\"}");
        }
        String readResponseCode = rcStorage.get(field63);
        rcStorage.remove(field63);

        return String.format("{\"message\" : \"Response code: %s %s\"}", readResponseCode, readRespondCode.read(readResponseCode));
    }

    @Scheduled(fixedDelay = 1)
    public void getMessageAuto() throws Exception {
//        SocketIO socketIO = manageSocket.getSocketByID("970409");
//            System.out.println("Hello");
        String messageReceive = messageEntity.getMessageInQueue();

        if (messageReceive == null || messageReceive.charAt(0) == 0) {
            return;
        }
        LOGGER.info(messageReceive);
//            System.out.println(messageReceive);
        processor.getInstance(mapperDataElement);



        MessageISO temp = processor.parsMessage(messageReceive);
        String f63 = temp.getDataElementContent().get(63);
//            LOGGER.info(f63);
        String f39 = temp.getDataElementContent().get(39);
        onlineLogRepository.addOnlineLog(f63, false, temp.getDataElementContent().get(0), messageReceive, f39);
        rcStorage.put(f63, f39);
        if (idStorage.containsKey(f63)) {
            long id = idStorage.get(f63);
            TransLog transLog = transLogRepository.findById(id).get();
            transLog.setF38(temp.getDataElementContent().get(38));
            transLog.setRC(f39);
            transLogRepository.save(transLog);
            idStorage.remove(f63);
        }
    }
//    @Override
//    public String sendMessageInImpMessageService(String messageSend) throws Exception {
//
//        int recentNumb = count++;
//        LOGGER.info("Processing request {}", recentNumb);
////        Thread.sleep(2000);
//        processor.getInstance(mapperDataElement);
//        LOGGER.info("Message receive " + recentNumb + ": " +  messageSend);
//
////        SocketIO socketIO = new SocketIO();
//        socketIO.sendMessage(messageSend);
//        String messageReceiv = socketIO.getMessage();
//
//        LOGGER.info("Message response " + recentNumb + ": " +  messageReceiv);
//
//        if (messageReceiv == null || messageReceiv.charAt(0) == 0) {
//            socketIO.reconnect();
//            LOGGER.info("Reconnect");
//            return "{\"message\" : \"Something wrong, please check your form and try again\"}";
//        }
//
//        MessageISO temp = processor.parsMessage(messageReceiv);
//        String readResponseCode = temp.getDataElementContent().get(39);
//
//        return String.format("Response code: %s %s", readResponseCode, readRespondCode.read(readResponseCode));
//    }

    //    @Override
//    public String sendMessage(List<DataReceive> data) {
//        try {
//            if (!socketIO.getConnected() || socketIO.isSocketChange()){
//                System.out.println(socketIO.getConnected());
//                return "{\"message\" : \"You have not connected\"}";
//            }
//            int recentNumb = count++;
//            LOGGER.info("Processing request {}", recentNumb);
//            Thread.sleep(5000);
//            MessageISO messageISO = dto.dataToMessage(data);
//            processor.getInstance(mapperDataElement);
//
//            String messageSend = processor.buildMessage(messageISO);
//            LOGGER.info("Message receive " + recentNumb + ": " +  messageSend);
//
////            SocketIO socketIO = new SocketIO();
//
//            socketIO.sendMessage(messageSend);
//            String messageReceiv = socketIO.getMessage();
//
//            LOGGER.info("Message response " + recentNumb + ": " +  messageReceiv);
//
//            if (messageReceiv == null || messageReceiv.charAt(0) == 0) {
//                socketIO = new SocketIO();
//                LOGGER.info("Reconnect");
//                return "{\"message\" : \"Something wrong, please check your form and try again\"}";
//            }
//
//            MessageISO temp = processor.parsMessage(messageReceiv);
//            String readResponseCode = temp.getDataElementContent().get(39);
//
//            return String.format("{\"message\" : \"Response code: %s %s\"}", readResponseCode, readRespondCode.read(readResponseCode));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
