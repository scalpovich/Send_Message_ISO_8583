package com.opw.financemessage.models;

public class Issuer {
    private String ip;
    private String id;
    private int port;

    public Issuer(String id, String ip, int port) {
        this.ip = ip;
        this.id = id;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public String getId() {
        return id;
    }

    public int getPort() {
        return port;
    }
}
