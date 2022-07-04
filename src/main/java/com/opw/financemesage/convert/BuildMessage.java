package com.opw.financemesage.convert;

import com.opw.financemesage.config.ConfigMapper;
import com.opw.financemesage.factory.Processor;
import com.opw.financemesage.models.DataReceive;
import com.opw.financemesage.models.MessageISO;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class BuildMessage {
    public void getData(List<DataReceive> data){
        MessageISO m = new MessageISO(data);
        Processor test = new Processor();
        ConfigMapper configMapper = new ConfigMapper();
        test.getInstance(configMapper.configMaper());
        System.out.println(test.buildMessage(m));
        MessageISO messageISO = test.parsMessage(test.buildMessage(m));
        messageISO.parsElement();
    }
}
