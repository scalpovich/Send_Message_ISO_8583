package com.opw.financemesage.models;


import com.opw.financemesage.convert.Convert;
import com.opw.financemesage.mapper.MapperDataElement;
import com.opw.financemesage.util.DataElementLength;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageISO {
    private String header;
    private String mti;
    private String primaryBitMap;
    private String secondaryBitMap;
    private String overralBitMap;
    private boolean isContainSecondaryBitMap = false;
    Map<Integer, String> dataElementContent = new HashMap<>();

    public boolean isContainSecondaryBitMap() {
        return isContainSecondaryBitMap;
    }

    public void setContainSecondaryBitMap(boolean containSecondaryBitMap) {
        isContainSecondaryBitMap = containSecondaryBitMap;
    }




    Convert convert = new Convert();

    public MessageISO() {
    }

    public MessageISO(List<DataReceive> data) {
        for(int i=0; i<data.size(); i++){
            this.getDataElementContent().put(data.get(i).getId(), data.get(i).getValue());
        }
        this.mti = this.dataElementContent.get(0);
        dataElementContent.remove(0);
//        if(this.dataElementContent.containsKey(1) == true){
//            this.isContainSecondaryBitMap = true;
//            dataElementContent.remove(1);
//        }
        dataElementContent.remove(1);
//        System.out.println(this.dataElementContent.containsKey(1) == true);
//        System.out.println(dataElementContent.get(1) == "1");
        buildBitMap();
        System.out.println(primaryBitMap);
        System.out.println(secondaryBitMap);
    }

    public boolean hasSecondaryBitMap(char bit){
        if (bit == '1'){
            return true;
        }
        return false;
    }

    public boolean hasField(int i){
        return overralBitMap.charAt(i) == '1';
    }
    public void buildBitMap(){
        Convert buildBit = new Convert();
        String bitMap = buildBit.biToHex(this);
        this.primaryBitMap = bitMap.substring(0,16);
        this.overralBitMap = buildBit.hexToBinary(this.primaryBitMap);
        if (bitMap.length()>16){
            this.secondaryBitMap= bitMap.substring(16,32);
            this.overralBitMap += buildBit.hexToBinary(this.secondaryBitMap);
        }
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getMti() {
        return mti;
    }

    public void setMti(String mti) {
        this.mti = mti;
    }

    public String getPrimaryBitMap() {
        return primaryBitMap;
    }

    public void setPrimaryBitMap(String primaryBitMap) {
        this.overralBitMap = convert.hexToBinary(primaryBitMap);
        this.primaryBitMap = primaryBitMap;
    }

    public String getSecondaryBitMap() {
        if (secondaryBitMap == null){
            return "";
        }
        return secondaryBitMap;
    }

    public void setSecondaryBitMap(String secondaryBitMap) {
        this.overralBitMap = convert.hexToBinary(secondaryBitMap);
        this.secondaryBitMap = secondaryBitMap;
    }

    public String getOverralBitMap() {
        return overralBitMap;
    }

    public void setOverralBitMap(String overralBitMap) {
        this.overralBitMap = overralBitMap;
    }

    public Map<Integer, String> getDataElementContent() {
        return dataElementContent;
    }

    public void setDataElement(Map<Integer, String> dataElement) {
        this.dataElementContent = dataElement;
    }

    public void parsElement (){
        for (int i = 1; i <= overralBitMap.length(); i++){
            if (overralBitMap.charAt(i-1) == '1'){
                System.out.println(i +":"+dataElementContent.get(i));
//                System.out.println("list.add(new DataReceive(" + String.valueOf(i) + ",\"" + dataElementContent.get(i) + "\"));");
            }
        }
    }

}
