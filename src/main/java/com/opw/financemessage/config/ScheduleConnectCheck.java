//package com.opw.financemessage.config;
//
//import com.opw.financemessage.factory.SystemParameters;
//import com.opw.financemessage.socket.SocketIO;
//import org.json.simple.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import java.io.IOException;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.TimeUnit;
//
//@Configuration
//@EnableScheduling
//public class ScheduleConnectCheck {
//    @Autowired
//    private SocketIO socketIO;
//
//    private int count = 0;
//    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleConnectCheck.class);
//    @Scheduled(fixedRate = 300000)
//    public void checkConnected() throws Exception {
//        socketIO.sendMessage("0063080082200001000000000400000000000000121616150731000106970409301");
//
//        CompletableFuture<String> completableFutureEchoMessage = CompletableFuture.completedFuture(socketIO.getMessage())
//                .completeOnTimeout("null",5, TimeUnit.SECONDS);
//
//        String responseEchoMessage = completableFutureEchoMessage.get();
//
////        LOGGER.info(responseEchoMessage);
//        if(responseEchoMessage == "null" || responseEchoMessage == null || responseEchoMessage == "\uFFFF"){
//            count ++;
//            System.out.println("Fail when send echo message");
//        }
//
//        else
//            count = 0;
//
//        SystemParameters parameters = new SystemParameters();
////        System.out.println((long)((JSONObject)parameters.getSystemParameters().get("connectCheck")).get("retryCount"));
//        if(count == (int)(long)((JSONObject)parameters.getSystemParameters().get("connectCheck")).get("retryCount")){
//            LOGGER.info("Socket disconnect, trying to reconnect ...");
//            socketIO.reconnect();
//        }
////        socketIO.sendMessage("02390200723C468128E0900016970409628135611101000000001000000007220324020000410324020722270627060210020006970400379704096281356111D300650010604015000002188090002610645064548434          NAPAS Bank             BNV           7047043CF1DC7C821E2BF2");
////        String receiveMessage = socketIO.getMessage();
////        LOGGER.info(receiveMessage);
////        if(receiveMessage == null || receiveMessage.charAt(0)==0){
////            socketIO = new SocketIO();
////            LOGGER.info("Reconnect by Schedule task");
////        }
////        else
////            LOGGER.info("Socket is connecting");
//    }
//}
