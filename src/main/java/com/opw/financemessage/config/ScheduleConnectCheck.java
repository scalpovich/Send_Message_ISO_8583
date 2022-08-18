//package com.opw.financemessage.config;
//
//import com.opw.financemessage.factory.SystemParameters;
//import com.opw.financemessage.models.DataReceive;
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
//    private SocketIO socketIO;
//    @Autowired
//    private ImplMessageService implMessageService;
//
//    private int count = 0;
//    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleConnectCheck.class);
//    @Scheduled(fixedRate = 5000)
//    public void checkConnected() throws Exception {
////        socketIO.sendMessage("0063080082200001000000000400000000000000121616150731000106970409301");
//        List<DataReceive> data = new ArrayList<DataReceive>();
//        data.add(new DataReceive(0, "0800"));
//        data.add(new DataReceive(7, DataElementType.DATE10.format(new Date(), TimeZone.getTimeZone("GMT"))));
//        data.add(new DataReceive(11, "310001"));
//        data.add(new DataReceive(32, "970409"));
//        data.add(new DataReceive(70, "301"));
//        String Field63 = DataElementType.DATE12.format(new Date(), TimeZone.getTimeZone("GMT"))
//                + String.valueOf((int) (Math.random() * (9999 - 1000)) + 1000) ;
//        data.add(new DataReceive(63, Field63));
//        Field63 = implMessageService.sendMessage(data, Field63);
//        String responseEchoMessage = implMessageService.getMessage(Field63, System.nanoTime());
//
//
////        LOGGER.info(responseEchoMessage);
//        if(responseEchoMessage == "{\"message\" : \"Response code: %s %s\"}" ||
//                responseEchoMessage == "null" ||
//                responseEchoMessage == null ||
//                responseEchoMessage == "\uFFFF"){
//            count ++;
//            System.out.println("Fail when send echo message");
//        }
//
//        else {
//            count = 0;
//            LOGGER.info("Everything is ok");
//        }
//
//        SystemParameters parameters = new SystemParameters();
////        System.out.println((long)((JSONObject)parameters.getSystemParameters().get("connectCheck")).get("retryCount"));
//        if(count >= (int)(long)((JSONObject)parameters.getSystemParameters().get("connectCheck")).get("retryCount")){
//            LOGGER.info("Socket disconnect, trying to reconnect ...");
//            socketIO.reconnect();
//        }
//    }
//}
