package com.opw.financemessage.controllers;

import com.opw.financemessage.models.DataReceive;
import com.opw.financemessage.services.Impl.MessageBalanceService;
import com.opw.financemessage.services.Impl.MessageFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class ControllerFile {

    private MessageFileService messageFileService;
    @Autowired
    public ControllerFile (MessageFileService messageFileService){
        this.messageFileService = messageFileService;
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageBalanceService.class);
    @RequestMapping (method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public @ResponseBody String uploadFile(
            @RequestParam MultipartFile file) throws Exception {
        
            return processFile(file.getInputStream());


    }

    public String processFile(final InputStream inputStream) throws Exception{
        String responseMessage = "{\"message\" : \"\\\\m";
        List<String> messages = parseCSVFile(inputStream);
        CompletableFuture<String> future[] = new CompletableFuture[messages.size()];
        String s[] =new String[messages.size()];
        for(int i=0; i<messages.size(); i++){
            future[i] = messageFileService.sendMessageInMessageFileService(messages.get(i));

//            s[i] = messageFileService.sendMessageInMessageFileService(messages.get(finalI)).get();

//            CompletableFuture<String> responseCode = messageFileService.sendMessageInMessageFileService(messages.get(i));
//            responseMessage += String.format("\"message %d\" : %s,", i+1, responseCode.get());
        }

//        CompletableFuture<Void> allFutures = CompletableFuture.allOf(future);
//        allFutures.get();

        for(int i=0; i<messages.size(); i++){

            responseMessage += String.format("essage %d : %s\\\\m", i+1, future[i].get());
        }

            responseMessage = responseMessage.substring(0,responseMessage.length() - 1) + "\"}";

        System.out.println(responseMessage);
        return responseMessage;
    }
    private List<String> parseCSVFile(final InputStream inputStream) throws Exception {
        final List<String> messages=new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line=br.readLine()) != null) {
                    messages.add(line);
//                    System.out.println(line);
                }
                return messages;
            }
        } catch(final IOException e) {
            LOGGER.error("Failed to parse CSV file {}", e);
            throw new Exception("Failed to parse CSV file {}", e);
        }
    }
}

