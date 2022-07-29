package com.opw.financemessage.services.Impl;

import com.opw.financemessage.models.DataReceive;
import com.opw.financemessage.util.DataElementType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@Service
public class MessageBalanceService extends ImplMessageService {
//    private static final Logger LOGGER = LoggerFactory.getLogger(MessageBalanceService.class);
//    private int count = 1;
    @Autowired()
    private Executor taskExecutor;

    @Async
    public CompletableFuture<String> send (List<DataReceive> data) throws Exception{
//        LOGGER.info("Processing request {}", count++);
//        Thread.sleep(5000);
        Date date = new Date();
        data.add(new DataReceive(7,DataElementType.DATE10.format(date, TimeZone.getTimeZone("GMT"))));
        data.add(new DataReceive(12,DataElementType.TIME.format(date, TimeZone.getTimeZone("GMT"))));
        data.add(new DataReceive(13, DataElementType.DATE4.format(date,TimeZone.getTimeZone("GMT"))));
        Comparator<DataReceive> compareById = new Comparator<DataReceive>() {
            @Override
            public int compare(DataReceive o1, DataReceive o2) {
                return o1.getId().compareTo(o2.getId());
            }
        };
        Collections.sort(data, compareById);
//        return CompletableFuture.completedFuture(this.sendMessage(data)).completeOnTimeout("{\"message\" : \"Time out\"}",10, TimeUnit.SECONDS);
        CompletableFuture<String> result = CompletableFuture.supplyAsync(()->this.sendMessage(data),taskExecutor).completeOnTimeout("{\"message\" : \"Time out\"}",5, TimeUnit.SECONDS);
        return result;
    }
}
