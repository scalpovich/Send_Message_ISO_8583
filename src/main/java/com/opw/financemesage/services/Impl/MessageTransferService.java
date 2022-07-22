package com.opw.financemesage.services.Impl;

import com.opw.financemesage.models.DataReceive;
import com.opw.financemesage.util.DataElementType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class MessageTransferService extends ImplMessageService{

    @Async
    public CompletableFuture<String> send(List<DataReceive> data){
        Date date = new Date();
        data.add(new DataReceive(7, DataElementType.DATE10.format(date, TimeZone.getTimeZone("GMT"))));
        data.add(new DataReceive(12,DataElementType.TIME.format(date, TimeZone.getTimeZone("GMT+7"))));
        data.add(new DataReceive(13, DataElementType.DATE4.format(date,TimeZone.getTimeZone("GMT+7"))));
        Comparator<DataReceive> compareById = new Comparator<DataReceive>() {
            @Override
            public int compare(DataReceive o1, DataReceive o2) {
                return o1.getId().compareTo(o2.getId());
            }
        };
        Collections.sort(data, compareById);
        return CompletableFuture.completedFuture(this.sendMessage(data));
    }
}
