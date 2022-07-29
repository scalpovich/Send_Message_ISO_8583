package com.opw.financemessage.convert;

import com.opw.financemessage.models.DataReceive;
import com.opw.financemessage.models.MessageISO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DTO {
    public MessageISO dataToMessage(List<DataReceive> data){
        MessageISO messageISO = new MessageISO();
        messageISO.setMti(data.get(0).getValue());
        for (int i = 1; i < data.size(); i++){
            int number = data.get(i).getId();
            String content = data.get(i).getValue();
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
