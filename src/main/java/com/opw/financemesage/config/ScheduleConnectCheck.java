package com.opw.financemesage.config;

import com.opw.financemesage.socket.SocketIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleConnectCheck {
    @Autowired
    private SocketIO socketIO;
    @Scheduled(fixedRate = 5000)
    public void checkConnected() {
        if(!socketIO.getSocket().isConnected())
            socketIO = new SocketIO();
    }
}
