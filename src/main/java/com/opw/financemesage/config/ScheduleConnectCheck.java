package com.opw.financemesage.config;

import com.opw.financemesage.socket.SocketIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleConnectCheck {
    @Autowired
    private SocketIO socketIO;
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleConnectCheck.class);
    @Scheduled(fixedRate = 5000)
    public void checkConnected() {
        if(!socketIO.getSocket().isConnected()) {
            LOGGER.info("Socket is disconnected. Trying to reconnect");
            socketIO = new SocketIO();
        }
//        socketIO.sendMessage("02390200723C468128E0900016970409628135611101000000001000000007220324020000410324020722270627060210020006970400379704096281356111D300650010604015000002188090002610645064548434          NAPAS Bank             BNV           7047043CF1DC7C821E2BF2");
//        String receiveMessage = socketIO.getMessage();
//        LOGGER.info(receiveMessage);
//        if(receiveMessage == null || receiveMessage.charAt(0)==0){
//            socketIO = new SocketIO();
//            LOGGER.info("Reconnect by Schedule task");
//        }
//        else
//            LOGGER.info("Socket is connecting");
    }
}
