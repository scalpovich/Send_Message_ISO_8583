package com.opw.financemessage.services;

import com.opw.financemessage.models.DataReceive;

import java.util.List;


public interface MessageService {
    String sendMessage(List<DataReceive> data);
    public String sendMessageInImpMessageService(String messageSend) throws Exception;

    String sendMessage(List<DataReceive> data, String filed63);

    String getMessage(String field63, long startTime);
}
