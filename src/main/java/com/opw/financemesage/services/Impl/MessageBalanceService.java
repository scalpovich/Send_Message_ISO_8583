package com.opw.financemesage.services.Impl;

import com.opw.financemesage.models.DataReceive;
import com.opw.financemesage.socket.SocketIO;
import com.opw.financemesage.util.DataElementType;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageBalanceService extends ImplMessageService {

    public String send (List<DataReceive> data){
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
        return this.sendMessage(data);
    }

}
