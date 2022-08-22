package com.opw.financemessage.util;

public enum DataElementLength {
    FIXED, VARIABLE;

    public String paddingLength (int value, int length){
        return String.format(String.format("%%0%dd",length),value);
    }
    public String processAmount(String value, int length){
        if (value.length() == length){
            return value;
        }
        int amount = (int) (Double.parseDouble(value) * 100);
        return String.format(String.format("%%0%dd",length),amount);
    }
}
