//package com.opw.financemessage.config;
//
//import com.opw.financemessage.convert.DTO;
//import com.opw.financemessage.factory.Processor;
//import com.opw.financemessage.factory.SystemParameters;
//import com.opw.financemessage.mapper.MapperDataElement;
//import com.opw.financemessage.models.DataReceive;
//import com.opw.financemessage.models.MessageISO;
//import com.opw.financemessage.services.Impl.ImplMessageService;
//import com.opw.financemessage.socket.SocketIO;
//import com.opw.financemessage.util.DataElementType;
//import org.json.simple.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.TimeZone;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.TimeUnit;
//
//@Configuration
//@EnableScheduling
//public class ScheduleConnectCheck {
//    @Autowired
//    private SystemParameters systemParameters;
//    @Autowired
//    private List<SocketIO> socketIOList;
//    @Autowired
//    private MapperDataElement mapperDataElement;
//    @Autowired
//    private DTO dto;
//    @Autowired
//    private Processor processor;
//    @Autowired
//    private ImplMessageService implMessageService;
//
//    private List<Integer> count = new ArrayList<>();
//    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleConnectCheck.class);
//
//    @Scheduled(fixedDelay = 5000)
//    public void checkConnected() throws Exception {
//        while (count.size()<socketIOList.size())
//            count.add(0);
////        socketIO.sendMessage("0063080082200001000000000400000000000000121616150731000106970409301");
//        for(int i=0; i< socketIOList.size(); i++){
//            List<DataReceive> data = new ArrayList<DataReceive>();
//            SocketIO socketIO = socketIOList.get(i);
//
//            data.add(new DataReceive(0, "0800"));
//            data.add(new DataReceive(7, DataElementType.DATE10.format(new Date(), TimeZone.getTimeZone("GMT"))));
//            data.add(new DataReceive(11, "310001"));
//            data.add(new DataReceive(32, "970409"));
//            data.add(new DataReceive(70, "301"));
//            String Field63 = DataElementType.DATE12.format(new Date(), TimeZone.getTimeZone("GMT"))
//                    + String.valueOf((int) (Math.random() * (9999 - 1000)) + 1000) ;
//            data.add(new DataReceive(63, Field63));
//            MessageISO messageISO = dto.dataToMessage(data);
//            processor.getInstance(mapperDataElement);
//
//            String messageSend = processor.buildMessage(messageISO);
//
////            LOGGER.info("Message receive " + recentNumb + ": " +  messageSend);
//            socketIO.sendMessage(messageSend);
//            String responseEchoMessage = implMessageService.getMessage(Field63, System.nanoTime());
//
//
////        LOGGER.info(responseEchoMessage);
//            if(responseEchoMessage == "{\"message\" : \"Response code: %s %s\"}" ||
//                    responseEchoMessage == "null" ||
//                    responseEchoMessage == null ||
//                    responseEchoMessage == "\uFFFF"){
//                count.set(i, count.get(i) +1 ) ;
//                System.out.println("Fail when send echo message");
//            }
//
//            else {
//                count.set(i, 0 ) ;
//                LOGGER.info("Everything is ok");
//            }
//
//
////        System.out.println((long)((JSONObject)parameters.getSystemParameters().get("connectCheck")).get("retryCount"));
//            if(count.get(i) >= systemParameters.getRetryCount()){
//                LOGGER.info("Socket disconnect, trying to reconnect ...");
//                socketIO.reconnect();
//            }
//        }
//
//    }
//}
