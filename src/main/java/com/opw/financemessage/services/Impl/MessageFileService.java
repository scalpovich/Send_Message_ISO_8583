package com.opw.financemessage.services.Impl;

import com.opw.financemessage.socket.SocketIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class MessageFileService extends ImplMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageFileService.class);


    @Async
    public CompletableFuture<String> sendMessageInMessageFileService(String s) throws Exception {
        try {
//            System.out.println("hehe");
            return CompletableFuture.completedFuture(this.sendMessageInImpMessageService(s));
        }
        catch (Exception e){

            return null;
        }
    }
}
