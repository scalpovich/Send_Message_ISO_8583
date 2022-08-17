package com.opw.financemessage.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "ONLINE_LOG")
public class OnlineLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String CH_ID;
    private Date REC_DATE;
    private String PAN;
    private String EXT_KEY;
    private String INT_KEY;
    private Long  PHYSICAL_CHANNEL;
    private String DIRECTION;
    private String MTID;
    private String SUB_TYPE;
    private String BUF1;
    private String BUF2;
    private String BUF3;
    private String BUF4;
    private String INFO;
    private String RC;

    public Long getID() {
        return ID;
    }

    public String getCH_ID() {
        return CH_ID;
    }

    public void setCH_ID(String CH_ID) {
        this.CH_ID = CH_ID;
    }

    public Date getREC_DATE() {
        return REC_DATE;
    }

    public void setREC_DATE(Date DATE) {
        this.REC_DATE = DATE;
    }

    public String getPAN() {
        return PAN;
    }

    public void setPAN(String PAN) {
        this.PAN = PAN;
    }

    public String getEXT_KEY() {
        return EXT_KEY;
    }

    public void setEXT_KEY(String EXT_KEY) {
        this.EXT_KEY = EXT_KEY;
    }

    public String getINT_KEY() {
        return INT_KEY;
    }

    public void setINT_KEY(String INT_KEY) {
        this.INT_KEY = INT_KEY;
    }

    public Long getPHYSICAL_CHANNEL() {
        return PHYSICAL_CHANNEL;
    }

    public void setPHYSICAL_CHANNEL(Long PHYSICAL_CHANNEL) {
        this.PHYSICAL_CHANNEL = PHYSICAL_CHANNEL;
    }

    public String getDIRECTION() {
        return DIRECTION;
    }

    public void setDIRECTION(String DIRECTION) {
        this.DIRECTION = DIRECTION;
    }

    public String getMTID() {
        return MTID;
    }

    public void setMTID(String MTID) {
        this.MTID = MTID;
    }

    public String getSUB_TYPE() {
        return SUB_TYPE;
    }

    public void setSUB_TYPE(String SUB_TYPE) {
        this.SUB_TYPE = SUB_TYPE;
    }

    public String getBUF1() {
        return BUF1;
    }

    public void setBUF1(String BUF1) {
        this.BUF1 = BUF1;
    }

    public String getBUF2() {
        return BUF2;
    }

    public void setBUF2(String BUF2) {
        this.BUF2 = BUF2;
    }

    public String getBUF3() {
        return BUF3;
    }

    public void setBUF3(String BUF3) {
        this.BUF3 = BUF3;
    }

    public String getBUF4() {
        return BUF4;
    }

    public void setBUF4(String BUF4) {
        this.BUF4 = BUF4;
    }

    public String getINFO() {
        return INFO;
    }

    public void setINFO(String INFO) {
        this.INFO = INFO;
    }

    public String getRC() {
        return RC;
    }

    public void setRC(String RC) {
        this.RC = RC;
    }

}
