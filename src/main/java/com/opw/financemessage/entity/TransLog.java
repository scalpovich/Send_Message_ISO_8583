package com.opw.financemessage.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "TRANS_LOG")
public class TransLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String MTI;
    private String F2;
    private String F3;
    private String F4;
    private String F49;
    private String F5;
    private String F50;
    private String F9;
    private String F6;
    private String F51;
    private String F10;
    private String F11;
    private String F12;
    private String F13;
    private String F15;
    private String F18;
    private String F22;
    private String F25;
    private String F41;
    private String ACQ;
    private String ISS;
    private String MID;
    private String BNB;
    private String F102;
    private String F103;
    private String F37;
    private String F38;

    public Long getID() {
        return ID;
    }

    public String getMTI() {
        return MTI;
    }

    public void setMTI(String MTI) {
        this.MTI = MTI;
    }

    public String getF2() {
        return F2;
    }

    public void setF2(String f2) {
        F2 = f2;
    }

    public String getF3() {
        return F3;
    }

    public void setF3(String f3) {
        F3 = f3;
    }

    public String getF4() {
        return F4;
    }

    public void setF4(String f4) {
        F4 = f4;
    }

    public String getF49() {
        return F49;
    }

    public void setF49(String f49) {
        F49 = f49;
    }

    public String getF5() {
        return F5;
    }

    public void setF5(String f5) {
        F5 = f5;
    }

    public String getF50() {
        return F50;
    }

    public void setF50(String f50) {
        F50 = f50;
    }

    public String getF9() {
        return F9;
    }

    public void setF9(String f9) {
        F9 = f9;
    }

    public String getF6() {
        return F6;
    }

    public void setF6(String f6) {
        F6 = f6;
    }

    public String getF51() {
        return F51;
    }

    public void setF51(String f51) {
        F51 = f51;
    }

    public String getF10() {
        return F10;
    }

    public void setF10(String f10) {
        F10 = f10;
    }

    public String getF11() {
        return F11;
    }

    public void setF11(String f11) {
        F11 = f11;
    }

    public String getF12() {
        return F12;
    }

    public void setF12(String f12) {
        F12 = f12;
    }

    public String getF13() {
        return F13;
    }

    public void setF13(String f13) {
        F13 = f13;
    }

    public String getF15() {
        return F15;
    }

    public void setF15(String f15) {
        F15 = f15;
    }

    public String getF18() {
        return F18;
    }

    public void setF18(String f18) {
        F18 = f18;
    }

    public String getF22() {
        return F22;
    }

    public void setF22(String f22) {
        F22 = f22;
    }

    public String getF25() {
        return F25;
    }

    public void setF25(String f25) {
        F25 = f25;
    }

    public String getF41() {
        return F41;
    }

    public void setF41(String f41) {
        F41 = f41;
    }

    public String getACQ() {
        return ACQ;
    }

    public void setACQ(String ACQ) {
        this.ACQ = ACQ;
    }

    public String getISS() {
        return ISS;
    }

    public void setISS(String ISS) {
        this.ISS = ISS;
    }

    public String getMID() {
        return MID;
    }

    public void setMID(String MID) {
        this.MID = MID;
    }

    public String getBNB() {
        return BNB;
    }

    public void setBNB(String BNB) {
        this.BNB = BNB;
    }

    public String getF102() {
        return F102;
    }

    public void setF102(String f102) {
        F102 = f102;
    }

    public String getF103() {
        return F103;
    }

    public void setF103(String f103) {
        F103 = f103;
    }

    public String getF37() {
        return F37;
    }

    public void setF37(String f37) {
        F37 = f37;
    }

    public String getF38() {
        return F38;
    }

    public void setF38(String f38) {
        F38 = f38;
    }

    public String getSRN() {
        return SRN;
    }

    public void setSRN(String SRN) {
        this.SRN = SRN;
    }

    public String getRSV1() {
        return RSV1;
    }

    public void setRSV1(String RSV1) {
        this.RSV1 = RSV1;
    }

    public String getRSV2() {
        return RSV2;
    }

    public void setRSV2(String RSV2) {
        this.RSV2 = RSV2;
    }

    public String getRSV3() {
        return RSV3;
    }

    public void setRSV3(String RSV3) {
        this.RSV3 = RSV3;
    }

    public String getRC() {
        return RC;
    }

    public void setRC(String RC) {
        this.RC = RC;
    }

    public Date getSYS_DATE() {
        return SYS_DATE;
    }

    public void setSYS_DATE(Date SYS_DATE) {
        this.SYS_DATE = SYS_DATE;
    }

    private String SRN;
    private String RSV1;
    private String RSV2;
    private String RSV3;
    private String RC;
    private Date SYS_DATE;



}
