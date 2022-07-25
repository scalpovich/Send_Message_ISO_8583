package com.opw.financemessage.models;

// Data receive from frontend

public class DataReceive implements Comparable<DataReceive>{
    private Integer id;
    private String value;

    public DataReceive(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public int compareTo(DataReceive o) {
        return this.getId().compareTo(o.getId());
    }
}
