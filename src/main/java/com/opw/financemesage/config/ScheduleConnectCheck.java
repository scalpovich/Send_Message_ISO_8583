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
//        else
//            LOGGER.info("Socket is connecting");
    }
}
