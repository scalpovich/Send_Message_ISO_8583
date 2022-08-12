package com.opw.financemessage.entity;

import javax.persistence.*;

@Entity
@Table(name = "card_info")
public class CardInfor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String F2;
    private String F14;
    private String F23;
    private String F35;
    private String F52;
    private String F102;
    private String F105;
    private String card_note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getF35() {
        return F35;
    }

    public void setF35(String f35) {
        F35 = f35;
    }

    public String getF105() {
        return F105;
    }

    public void setF105(String f105) {
        F105 = f105;
    }

    public String getF2() {
        return F2;
    }

    public void setF2(String f2) {
        F2 = f2;
    }

    public String getF14() {
        return F14;
    }

    public void setF14(String f14) {
        F14 = f14;
    }

    public String getF23() {
        return F23;
    }

    public void setF23(String f23) {
        F23 = f23;
    }

    public String getF52() {
        return F52;
    }

    public void setF52(String f52) {
        F52 = f52;
    }

    public String getF102() {
        return F102;
    }

    public void setF102(String f102) {
        F102 = f102;
    }

    public String getCard_note() {
        return card_note;
    }

    public void setCard_note(String card_note) {
        this.card_note = card_note;
    }

    @Override
    public String toString() {
        return "CardInfor{" +
                "id=" + id +
                ", F2='" + F2 + '\'' +
                ", F14='" + F14 + '\'' +
                ", F23='" + F23 + '\'' +
                ", F52='" + F52 + '\'' +
                ", F102='" + F102 + '\'' +
                ", card_note='" + card_note + '\'' +
                '}';
    }
}
