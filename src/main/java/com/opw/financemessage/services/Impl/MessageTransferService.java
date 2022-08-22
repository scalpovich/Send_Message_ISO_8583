package com.opw.financemessage.services.Impl;

import com.opw.financemessage.models.DataReceive;
import com.opw.financemessage.util.DataElementType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class MessageTransferService extends ImplMessageService{

    @Async("sendTaskExecutor")
    public CompletableFuture<String> send (List<DataReceive> data){
        Date date = new Date();
        data.add(new DataReceive(7, DataElementType.DATE10.format(date, TimeZone.getTimeZone("GMT"))));
        data.add(new DataReceive(12,DataElementType.TIME.format(date, TimeZone.getTimeZone("GMT"))));
        data.add(new DataReceive(13, DataElementType.DATE4.format(date,TimeZone.getTimeZone("GMT"))));
        String Field63 = DataElementType.DATE12.format(date, TimeZone.getTimeZone("GMT"))
                + String.valueOf((int) (Math.random() * (9999 - 1000)) + 1000) ;
//        System.out.println();
//        System.out.println(Field63);
        data.add(new DataReceive(63, Field63));

        Comparator<DataReceive> compareById = new Comparator<DataReceive>() {
            @Override
            public int compare(DataReceive o1, DataReceive o2) {
                return o1.getId().compareTo(o2.getId());
            }
        };
        Collections.sort(data, compareById);
        return CompletableFuture.completedFuture(this.sendMessage(data,Field63));
    }
    @Async("getTaskExecutor")
    public CompletableFuture<String> getMessageByField63(String field63, long startTime) {
        return CompletableFuture.completedFuture(this.getMessage(field63, startTime));
    }
}
