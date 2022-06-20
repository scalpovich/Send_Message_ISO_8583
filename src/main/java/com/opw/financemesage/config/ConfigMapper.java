package com.opw.financemesage.config;

import com.opw.financemesage.mapper.MapperDataElement;
import com.opw.financemesage.models.DataElement;
import com.opw.financemesage.util.DataElementLength;
import com.opw.financemesage.util.DataElementType;


public class ConfigMapper {
    public MapperDataElement configMaper(){
        MapperDataElement m = new MapperDataElement();
        m.getDataElement()
                .put(2, new DataElement()
                        .setNumber(2)
                        .setPrefixLength(2)
                        .setLengthType(DataElementLength.VARIABLE)
                        .setType(DataElementType.LLVAR)
        );
        m.getDataElement()
                .put(3, new DataElement()
                        .setNumber(3)
                        .setLength(6)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.NUMERIC)
                );
        m.getDataElement()
                .put(4, new DataElement()
                        .setNumber(4)
                        .setLength(12)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.NUMERIC)
                );
        m.getDataElement()
                .put(5, new DataElement()
                        .setNumber(5)
                        .setLength(12)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.NUMERIC)
                );
        m.getDataElement()
                .put(6, new DataElement()
                        .setNumber(6)
                        .setLength(12)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.NUMERIC)
                );
        m.getDataElement()
                .put(7, new DataElement()
                        .setNumber(7)
                        .setLength(10)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.DATE10)
                );
        m.getDataElement()
                .put(9, new DataElement()
                        .setNumber(9)
                        .setLength(8)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.NUMERIC)
                );
        m.getDataElement()
                .put(10,new DataElement()
                        .setNumber(10)
                        .setLength(8)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.NUMERIC)
                );
        m.getDataElement()
                .put(11,
                        new DataElement()
                        .setNumber(11)
                        .setLength(6)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.NUMERIC)
                );
        m.getDataElement()
                .put(12,
                        new DataElement()
                        .setNumber(12)
                        .setLength(6)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.DATE6)
                );
        m.getDataElement()
                .put(13,
                        new DataElement()
                        .setNumber(13)
                        .setLength(4)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.DATE4)
                );
        m.getDataElement()
                .put(14, new DataElement()
                        .setNumber(14)
                        .setLength(4)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.DATE4)
                );

        m.getDataElement()
                .put(15, new DataElement()
                        .setNumber(15)
                        .setLength(4)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.DATE4)
                );
        m.getDataElement()
                .put(18, new DataElement()
                        .setNumber(18)
                        .setLength(4)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.NUMERIC)
                );
        m.getDataElement()
                .put(19, new DataElement()
                        .setNumber(19)
                        .setLength(3)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.NUMERIC)
                );
        m.getDataElement()
                .put(22, new DataElement()
                        .setNumber(22)
                        .setLength(3)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.NUMERIC)
                );
        m.getDataElement()
                .put(23, new DataElement()
                        .setNumber(23)
                        .setLength(3)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.NUMERIC)
                );
        m.getDataElement()
                .put(25, new DataElement()
                        .setNumber(25)
                        .setLength(2)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.NUMERIC)
                );
        m.getDataElement()
                .put(26, new DataElement()
                        .setNumber(26)
                        .setLength(2)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.NUMERIC)
                );
        m.getDataElement()
                .put(32, new DataElement()
                        .setNumber(32)
                        .setPrefixLength(2)
                        .setLengthType(DataElementLength.VARIABLE)
                        .setType(DataElementType.LLVAR)
                );
        m.getDataElement()
                .put(35, new DataElement()
                        .setNumber(35)
                        .setPrefixLength(2)
                        .setLengthType(DataElementLength.VARIABLE)
                        .setType(DataElementType.LLVAR)
                );
        m.getDataElement()
                .put(36, new DataElement()
                        .setNumber(36)
                        .setPrefixLength(3)
                        .setLengthType(DataElementLength.VARIABLE)
                        .setType(DataElementType.LLLVAR)
                );
        m.getDataElement()
                .put(37, new DataElement()
                        .setNumber(37)
                        .setLength(12)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.ALPHA)
                );
        m.getDataElement()
                .put(38, new DataElement()
                        .setNumber(38)
                        .setLength(6)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.ALPHA)
                );
        m.getDataElement()
                .put(39, new DataElement()
                        .setNumber(39)
                        .setLength(2)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.ALPHA)
                );
        m.getDataElement()
                .put(41, new DataElement()
                        .setNumber(41)
                        .setLength(8)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.ALPHA)
                );
        m.getDataElement()
                .put(42, new DataElement()
                        .setNumber(42)
                        .setLength(15)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.ALPHA)
                );
        m.getDataElement()
                .put(43, new DataElement()
                        .setNumber(43)
                        .setLength(40)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.ALPHA)
                );
        m.getDataElement()
                .put(45, new DataElement()
                                .setNumber(45)
                                .setPrefixLength(2)
                                .setLengthType(DataElementLength.VARIABLE)
                                .setType(DataElementType.LLVAR)
                        );
        m.getDataElement()
                .put(48, new DataElement()
                                .setNumber(48)
                                .setPrefixLength(3)
                                .setLengthType(DataElementLength.VARIABLE)
                                .setType(DataElementType.LLLVAR)
                        );
        m.getDataElement()
                .put(49, new DataElement()
                        .setNumber(49)
                        .setLength(3)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.NUMERIC)
                );
        m.getDataElement()
                .put(50, new DataElement()
                        .setNumber(50)
                        .setLength(3)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.NUMERIC)
                );
        m.getDataElement()
                .put(51, new DataElement()
                                .setNumber(51)
                                .setLength(3)
                                .setLengthType(DataElementLength.FIXED)
                                .setType(DataElementType.NUMERIC)
                        );
        m.getDataElement()
                .put(52, new DataElement()
                        .setNumber(52)
                        .setLength(16)
                        .setLengthType(DataElementLength.FIXED)
                        .setType(DataElementType.ALPHA)
                );
        m.getDataElement()
                .put(54, new DataElement()
                                .setNumber(54)
                                .setPrefixLength(3)
                                .setLengthType(DataElementLength.VARIABLE)
                                .setType(DataElementType.LLLVAR)
                        );
        m.getDataElement()
                .put(55,new DataElement()
                                .setNumber(55)
                                .setPrefixLength(3)
                                .setLengthType(DataElementLength.VARIABLE)
                                .setType(DataElementType.LLLVAR)
                        );
        m.getDataElement()
                .put(60,new DataElement()
                        .setNumber(60)
                        .setPrefixLength(3)
                        .setLengthType(DataElementLength.VARIABLE)
                        .setType(DataElementType.LLLVAR)
                );
        m.getDataElement()
                .put(62,new DataElement()
                        .setNumber(62)
                        .setPrefixLength(2)
                        .setLengthType(DataElementLength.VARIABLE)
                        .setType(DataElementType.LLVAR)
                );
        m.getDataElement()
                .put(63, new DataElement()
                        .setNumber(63)
                        .setPrefixLength(3)
                        .setLengthType(DataElementLength.VARIABLE)
                        .setType(DataElementType.LLLVAR)
                );
        return m;

    }
}
