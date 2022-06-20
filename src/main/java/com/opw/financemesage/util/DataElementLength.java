package com.opw.financemesage.util;

public enum DataElementLength {
    FIXED, VARIABLE;

    public String paddingLength (int value, int length){
        return String.format(String.format("%%0%dd",length),value);
    }

}
