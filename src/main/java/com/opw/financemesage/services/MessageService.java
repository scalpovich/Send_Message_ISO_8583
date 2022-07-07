package com.opw.financemesage.services;

import com.opw.financemesage.models.DataReceive;

import java.util.List;


public interface MessageService {
    String sendMessage(List<DataReceive> data);
    List<DataReceive> getMesssage();
}
