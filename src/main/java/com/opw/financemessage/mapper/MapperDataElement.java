package com.opw.financemessage.mapper;

import com.opw.financemessage.models.DataElement;

import java.util.HashMap;
import java.util.Map;

public class MapperDataElement {
    private Map<Integer, DataElement> dataElement = new HashMap<Integer,DataElement>();

    public Map<Integer, DataElement> getDataElement() {
        return dataElement;
    }

    public void setDataElement(Map<Integer, DataElement> dataElement) {
        this.dataElement = dataElement;
    }
}
