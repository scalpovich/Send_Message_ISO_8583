package com.opw.financemessage.factory;

import com.opw.financemessage.mapper.MapperDataElement;
import com.opw.financemessage.models.DataElement;
import com.opw.financemessage.models.MessageISO;
import com.opw.financemessage.util.DataElementLength;
import org.springframework.stereotype.Component;

@Component
public class Processor {
    private static final Integer HEADER_LENGTH = 4;
    private static final Integer MTI_LENGTH = 4;
    private static final Integer BITMAP_LENGTH = 16;
    private static MapperDataElement map;
    private static Processor instance;

    public Processor getInstance(MapperDataElement map) {
        if (instance == null) {
            instance = new Processor();
        }
        instance.map = map;
        return instance;
    }
    public MessageISO parsMessage(String message){
        int currentPosition = 0;

        MessageISO m = new MessageISO();

        m.setHeader(message.substring(0,HEADER_LENGTH));
        currentPosition += HEADER_LENGTH;

        m.setMti(message.substring(currentPosition, currentPosition+MTI_LENGTH));
        currentPosition += MTI_LENGTH;

        m.setPrimaryBitMap(message.substring(currentPosition, currentPosition+BITMAP_LENGTH));
        currentPosition += BITMAP_LENGTH;

        // check has second bitmap
        if(m.hasSecondaryBitMap(m.getOverralBitMap().charAt(0))){
            m.setSecondaryBitMap(message.substring(currentPosition, currentPosition+BITMAP_LENGTH));
            currentPosition += BITMAP_LENGTH;
        }

        // get field ISO in message
        for(int i = 2; i <= m.getOverralBitMap().length(); i++){
            if (!m.hasField(i-1)){
                continue;
            }
            DataElement de = map.getDataElement().get(i);
            if (de.getLengthType().equals("FIXED")){
                String data = message.substring(currentPosition, currentPosition+de.getLength());
                m.getDataElementContent().put(i,data);
                currentPosition += de.getLength();
            }
            if (de.getLengthType().equals("VARIABLE")){
                int length = Integer.parseInt(message.substring(currentPosition, currentPosition+de.getPrefixLength()));
                currentPosition += de.getPrefixLength();
                String data = message.substring(currentPosition, currentPosition+length);
                m.getDataElementContent().put(i, data);
                currentPosition += length;
            }
        }
        return m;
    }

    public String buildMessage(MessageISO messageISO){
        StringBuilder builder = new StringBuilder();
        messageISO.buildBitMap();

        builder.append(messageISO.getMti());
        builder.append(messageISO.getPrimaryBitMap());
        builder.append(messageISO.getSecondaryBitMap());

        for(int i = 2; i < messageISO.getOverralBitMap().length() ; i++){
            if(!messageISO.hasField(i-1)){
                continue;
            }
            DataElement de = map.getDataElement().get(i);
            if(de.getLengthType().equals("FIXED")){
                if (i == 4 || i == 5 || i == 6){
                    builder.append(DataElementLength.FIXED.processAmount(messageISO.getDataElementContent().get(i),de.getLength()));
                    continue;
                }
                builder.append(messageISO.getDataElementContent().get(i));
            }
            if(de.getLengthType().equals("VARIABLE")){
                String data = messageISO.getDataElementContent().get(i);
                String dataLength = DataElementLength.VARIABLE.paddingLength(data.length(),de.getPrefixLength());
                builder.append(dataLength);
                builder.append(data);
            }
        }
        String header = String.valueOf(builder.length());
        while (header.length()<4){
            header = "0" + header;
        }
        messageISO.setHeader(header);
        return header + builder.toString();
    }

}
