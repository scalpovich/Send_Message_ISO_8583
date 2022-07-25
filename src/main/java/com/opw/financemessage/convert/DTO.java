package com.opw.financemessage.convert;

import com.opw.financemessage.models.DataReceive;
import com.opw.financemessage.models.MessageISO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DTO {
    public MessageISO dataToMessage(List<DataReceive> datas){
        MessageISO messageISO = new MessageISO();
        messageISO.setMti(datas.get(0).getValue());
        for (int i = 1; i < datas.size(); i++){
            int number = datas.get(i).getId();
            String content = datas.get(i).getValue();
            messageISO.getDataElementContent().put(number,content);
        }
        return messageISO;
    }

    public List<DataReceive> messageToData(MessageISO messageISO){
        List<DataReceive> data = new ArrayList<>();
        data.add(new DataReceive(1,messageISO.getMti()));
        for (Integer key : messageISO.getDataElementContent().keySet()){
            data.add(new DataReceive(key, messageISO.getDataElementContent().get(key)));
        }
        return data;
    }
}
