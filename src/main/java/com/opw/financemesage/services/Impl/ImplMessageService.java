package com.opw.financemesage.services.Impl;

import com.opw.financemesage.config.ConfigMapper;
import com.opw.financemesage.convert.BuildMessage;
import com.opw.financemesage.factory.Processor;
import com.opw.financemesage.mapper.MapperDataElement;
import com.opw.financemesage.models.DataReceive;
import com.opw.financemesage.models.MessageISO;
import com.opw.financemesage.services.MessageService;
import com.opw.financemesage.socket.SocketIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ImplMessageService implements MessageService {

    @Autowired
    private SocketIO socketIO;

    @Autowired
    private MapperDataElement mapperDataElement;

    @Autowired
    private Processor processor;

    @Autowired
    private BuildMessage buildMess;

    @Override
    public MessageISO sendMessage(MessageISO message) {
        try {
            processor.getInstance(mapperDataElement);
            String mesageISO = processor.buildMessage(message);
            socketIO.sendMessage(mesageISO);

            return processor.parsMessage(socketIO.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void buildMessage(List<DataReceive> data) {
        buildMess.getData(data);
    }

    @Override
    public List<DataReceive> getMessage(){
        Processor test = new Processor();
        ConfigMapper configMapper = new ConfigMapper();
        test.getInstance(configMapper.configMaper());
        MessageISO messageISO = test.parsMessage("026802007ABA448128E0D0021697040932704448260100000000100000000000100000000524085210000000015371571552100524052460110210006970468279704093270444826=290150057100000050754500000001AB                                              BNV 704704704DB0B60B3204663F5016AAcB6wDcYKtpWwAA");
        Map map = messageISO.getDataElementContent();
        List<DataReceive> list = new ArrayList<>();
        for (Object key : map.keySet()) {
            list.add(new DataReceive((int) key, (String) map.get(key)));
        }
        list.sort(Comparator.comparing(DataReceive::getId));
        return list;
    }
}
