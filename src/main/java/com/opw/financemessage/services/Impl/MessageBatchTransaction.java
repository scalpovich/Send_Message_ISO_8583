package com.opw.financemessage.services.Impl;

import com.opw.financemessage.entity.CardInfor;
import com.opw.financemessage.models.DataReceive;
import com.opw.financemessage.repository.CardInfoRepository;
import com.opw.financemessage.util.DataElementType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class MessageBatchTransaction extends ImplMessageBatchService {

    @Async
    public CompletableFuture<String> send(int transaction, String contentTransaction, CardInfor cardInfor) throws Exception {
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(contentTransaction);
        List<DataReceive> listConfig;
        listConfig = new ArrayList<>();
        array.forEach(elementdata -> {
            JSONObject elem = (JSONObject) elementdata;
            DataReceive dataReceive = new DataReceive(Math.toIntExact((Long) elem.get("id")), (String) elem.get("value"));
            listConfig.add(dataReceive);
        });

        Date date = new Date();
        if (transaction == 1 || transaction == 3 || transaction == 6) {
            listConfig.add(new DataReceive(7, DataElementType.DATE10.format(date, TimeZone.getTimeZone("GMT"))));
            listConfig.add(new DataReceive(12, DataElementType.TIME.format(date, TimeZone.getTimeZone("GMT"))));
            listConfig.add(new DataReceive(13, DataElementType.DATE4.format(date, TimeZone.getTimeZone("GMT"))));
        } else {
            listConfig.add(new DataReceive(7, DataElementType.DATE10.format(date, TimeZone.getTimeZone("GMT"))));
            listConfig.add(new DataReceive(12, DataElementType.TIME.format(date, TimeZone.getTimeZone("GMT+7"))));
            listConfig.add(new DataReceive(13, DataElementType.DATE4.format(date, TimeZone.getTimeZone("GMT+7"))));
        }
        Random random = new Random();
        listConfig.add(new DataReceive(37,"123456"+ String.valueOf(random.nextInt(900) + 100) + String.valueOf(random.nextInt(900) + 100)));
        if (!cardInfor.getF2().isEmpty())
            listConfig.add(new DataReceive(2, cardInfor.getF2()));
//        if (!cardInfor.getF14().isEmpty())
//            listConfig.add(new DataReceive(14, cardInfor.getF14()));
//        if (!cardInfor.getF23().isEmpty())
//            listConfig.add(new DataReceive(23, cardInfor.getF23()));
        if (!cardInfor.getF35().isEmpty())
            listConfig.add(new DataReceive(35, cardInfor.getF35()));
        if (!cardInfor.getF52().isEmpty())
            listConfig.add(new DataReceive(52, cardInfor.getF52()));
//            if(!cardInfor.getF102().isEmpty())
//                listConfig.add(new DataReceive(102,cardInfor.getF102()));
        if (!cardInfor.getF105().isEmpty() && transaction == 5)
            listConfig.add(new DataReceive(105, cardInfor.getF105()));

        Comparator<DataReceive> compareById = new Comparator<DataReceive>() {
            @Override
            public int compare(DataReceive o1, DataReceive o2) {
                return o1.getId().compareTo(o2.getId());
            }
        };
        Collections.sort(listConfig, compareById);
        return CompletableFuture.completedFuture(sendMessage(listConfig));
    }

    @Async
    public CompletableFuture<String> get(int numberTransaction){
        return CompletableFuture.completedFuture(getMessage(numberTransaction));
    }
}
