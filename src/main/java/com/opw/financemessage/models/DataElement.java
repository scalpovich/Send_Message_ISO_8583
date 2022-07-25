package com.opw.financemessage.models;

import com.opw.financemessage.util.DataElementLength;
import com.opw.financemessage.util.DataElementType;

public class DataElement {
    private Integer number;
    private Integer length = 0;
    private Integer prefixLength = 0;
    private DataElementType type;
    private DataElementLength lengthType;

    public Integer getNumber() {
        return number;
    }

    public DataElement setNumber(Integer number) {
        this.number = number;
        return this;
    }

    public Integer getLength() {
        return length;
    }

    public DataElement setLength(Integer length) {
        this.length = length;
        return this;
    }

    public Integer getPrefixLength() {
        return prefixLength;
    }

    public DataElement setPrefixLength(Integer prefixLength) {
        this.prefixLength = prefixLength;
        return this;
    }

    public DataElementType getType() {
        return type;
    }

    public DataElement setType(DataElementType type) {
        this.type = type;
        return this;
    }

    public DataElementLength getLengthType() {
        return lengthType;
    }

    public DataElement setLengthType(DataElementLength lengthType) {
        this.lengthType = lengthType;
        return this;
    }
}
