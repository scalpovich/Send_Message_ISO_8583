package com.opw.financemesage.services;

import com.opw.financemesage.models.DataReceive;

import java.util.List;


public interface MessageService {
    void sendMessage(List<DataReceive> data);
    List<DataReceive> getMesssage();
}
