package com.opw.financemessage.services;

import com.opw.financemessage.models.DataReceive;

import java.util.List;

public interface MessageBatchService {
    String sendMessage(List<DataReceive> data);
    String getMessage(int numberTransaction);
}
