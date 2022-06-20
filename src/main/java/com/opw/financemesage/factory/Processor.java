package com.opw.financemesage.factory;

import com.opw.financemesage.config.ConfigMapper;
import com.opw.financemesage.mapper.MapperDataElement;
import com.opw.financemesage.models.DataElement;
import com.opw.financemesage.models.MessageISO;
import com.opw.financemesage.util.DataElementLength;
import org.springframework.stereotype.Component;

@Component
public class Processor {
    private static final Integer HEADER_LENGTH = 4;
    private static final Integer MTI_LENGTH = 4;
    private static final Integer BITMAP_LENGTH = 16;
    private static MapperDataElement map;
    private static Processor instance;

    public  Processor getInstance(MapperDataElement map) {
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
        if(m.hasSecondaryBitMap(m.getPrimaryBitMap().charAt(0))){
            m.setSecondaryBitMap(message.substring(currentPosition, currentPosition+BITMAP_LENGTH));
            currentPosition += BITMAP_LENGTH;
        }

        // get field ISO in message
        for(int i = 2; i <= m.getOverralBitMap().length(); i++){

            if (!m.hasField(i-1)){
                continue;
            }
            DataElement de = map.getDataElement().get(i);
            if (DataElementLength.FIXED.equals(de.getLengthType())){
                String data = message.substring(currentPosition, currentPosition+de.getLength());
                m.getDataElementContent().put(i,data);
                currentPosition += de.getLength();
            }
            if (DataElementLength.VARIABLE.equals(de.getLengthType())){
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
            if(DataElementLength.FIXED.equals(de.getLengthType())){
                builder.append(messageISO.getDataElementContent().get(i));
            }
            if(DataElementLength.VARIABLE.equals(de.getLengthType())){
                String data = messageISO.getDataElementContent().get(i);
                String dataLength = DataElementLength.VARIABLE.paddingLength(data.length(),de.getPrefixLength());
                builder.append(dataLength);
                builder.append(data);
            }
        }
        return builder.toString();
    }

//    public static void main(String[] args) {
//        String message = "026802007ABA448128E0D0021697040932704448260100000000100000000000100000000524085210000000015371571552100524052460110210006970468279704093270444826=290150057100000050754500000001AB                                              BNV 704704704DB0B60B3204663F5016AAcB6wDcYKtpWwAA";
//        ConfigMapper config = new ConfigMapper();
//        Processor test = Processor.getInstance(config.configMaper());
//        MessageISO messageISO = test.parsMessage(message);
//        messageISO.parsElement();
//
//        MessageISO messageISO1 = new MessageISO();
//
//        messageISO1.setMti("0200");
//        messageISO1.getDataElementContent().put(2, "9704093270444826");
//        messageISO1.getDataElementContent().put(3, "010000");
//        messageISO1.getDataElementContent().put(4, "000010000000");
//        messageISO1.getDataElementContent().put(5, "000010000000");
//        messageISO1.getDataElementContent().put(7, "0524085210");
//        messageISO1.getDataElementContent().put(9, "00000001");
//        messageISO1.getDataElementContent().put(11, "537157");
//        messageISO1.getDataElementContent().put(12, "155210");
//        messageISO1.getDataElementContent().put(13, "0524");
//        messageISO1.getDataElementContent().put(15, "0524");
//        messageISO1.getDataElementContent().put(18, "6011");
//        messageISO1.getDataElementContent().put(22, "021");
//        messageISO1.getDataElementContent().put(25, "00");
//        messageISO1.getDataElementContent().put(32, "970468");
//        messageISO1.getDataElementContent().put(35, "9704093270444826=2901500571");
//        messageISO1.getDataElementContent().put(37, "000000507545");
//        messageISO1.getDataElementContent().put(41, "00000001");
//        messageISO1.getDataElementContent().put(42, "AB             ");
//        messageISO1.getDataElementContent().put(43, "                                 BNV 704");
//        messageISO1.getDataElementContent().put(49, "704");
//        messageISO1.getDataElementContent().put(50, "704");
//        messageISO1.getDataElementContent().put(52, "DB0B60B3204663F5");
//        messageISO1.getDataElementContent().put(63, "AAcB6wDcYKtpWwAA");
//
//
//        String newMessage = test.buildMessage(messageISO1);
//        System.out.println(newMessage);
//    }
}
