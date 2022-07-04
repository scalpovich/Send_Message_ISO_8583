package com.opw.financemesage.services;

import com.opw.financemesage.models.DataReceive;
import com.opw.financemesage.models.MessageISO;

import java.util.List;

public interface MessageService {
    MessageISO sendMessage(MessageISO message);

    void buildMessage(List<DataReceive> data);

    List<DataReceive> getMessage();
}
