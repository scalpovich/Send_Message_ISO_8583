package com.opw.financemesage.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public enum DataElementType {
    NUMERIC,
    ALPHA,
    DATE6,
    DATE14,
    DATE12,
    DATE10,
    DATE4,
    DATE_EXP,
    TIME,
    AMOUNT,
    LLVAR,
    LLLVAR,
    BINARY;

    public String format(final Date value, final TimeZone tz){
        final SimpleDateFormat sdf;
        if (this == DATE10){
              sdf = new SimpleDateFormat("MMddHHmmss");
        } else if (this == DATE4) {
            sdf = new SimpleDateFormat("MMdd");
        } else if (this == DATE_EXP) {
            sdf = new SimpleDateFormat("yyMM");
        } else if (this == TIME) {
            sdf = new SimpleDateFormat("HHmmss");
        } else if (this == DATE12) {
            sdf = new SimpleDateFormat("yyMMddHHmmss");
        } else if (this == DATE14) {
            sdf = new SimpleDateFormat("YYYYMMddHHmmss");
        } else if (this == DATE6) {
            sdf = new SimpleDateFormat("yyMMdd");
        } else {
            throw new IllegalArgumentException("Cannot format date as " + this);
        }
        if (tz != null) {
            sdf.setTimeZone(tz);
        }
        return sdf.format(value);
    }

    public String format(String value, int length){
        if(this == ALPHA){
            if (value == null) {
                value = "";
            }
            if (value.length() > length) {
                return value.substring(0, length);
            } else if (value.length() == length) {
                return value;
            } else {
                return String.format(String.format("%%-%ds", length), value);
            }
        }else if (this == LLVAR || this == LLLVAR) {
            return value;
        }else if (this == NUMERIC) {
            char[] c = new char[length];
            char[] x = value.toCharArray();
            if (x.length > length) {
                throw new IllegalArgumentException("Numeric value is larger than intended length: " + value + " LEN " + length);
            }
            int lim = c.length - x.length;
            for (int i = 0; i < lim; i++) {
                c[i] = '0';
            }
            System.arraycopy(x, 0, c, lim, x.length);
            return new String(c);
        }else if (this == AMOUNT) {
            return DataElementType.NUMERIC.format(new BigDecimal(value).movePointRight(2).longValue(), 12);
        }else if (this == BINARY){
            if (value == null){
                value = "";
            }
            if (value.length() > length){
                return value.substring(0,length);
            }
            char[] c = new char[length];
            int end = value.length();
            System.arraycopy(value.toCharArray(),0,c,0,end);
            for (int i = end; i < c.length; i++){
                c[i] = '0';
            }
            return new String(c);
        }else {
            return value;
        }
    }

    public String format(long value, int length){
        if (this == NUMERIC){
            String x = String.format(String.format("%%0%dd",length),value);
            if (x.length() > length){
                throw new IllegalArgumentException("Numeric value is larger than intended length");
            }
            return x;
        }else if (this == ALPHA || this == LLVAR || this == LLLVAR) {
            return format(Long.toString(value), length);
        } else if (this == AMOUNT) {
            return String.format("%010d00", value);
        }
        throw new IllegalArgumentException("Cannot format number as " + this);

    }

}
