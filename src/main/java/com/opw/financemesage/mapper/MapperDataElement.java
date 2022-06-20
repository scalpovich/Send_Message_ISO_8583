package com.opw.financemesage.mapper;

import com.opw.financemesage.models.DataElement;

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
