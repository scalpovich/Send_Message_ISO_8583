package com.opw.financemessage.factory;

import com.opw.financemessage.models.Issuer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SystemParameters {

    private JSONObject systemParameters;
    private int timeoutMessage;

    private int maxSendThread;
    private int coreSendThread;
    private int queueSendThread;

    private int maxGetThread;
    private int coreGetThread;
    private int queueGetThread;


    private int retryCount;
    private int fixRate;

    private List<Issuer> issuerList;
    public SystemParameters() {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = null;
        try {
            reader = new FileReader(("src/main/resources/SystemParameter.json"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            systemParameters = (JSONObject) jsonParser.parse(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        issuerList = new ArrayList<>();
        JSONArray objSocket = (JSONArray) systemParameters.get("socket");
        objSocket.forEach(i -> {
            String id = (String)((JSONObject)i).get("id");
            String ip = (String)((JSONObject)i).get("ip");
            int port = (int)(long)((JSONObject)i).get("port");
            issuerList.add(new Issuer(id, ip, port));
        });

        timeoutMessage = (int)(long)systemParameters.get("timeoutMessage");

        JSONObject sendThread = (JSONObject) systemParameters.get("sendThread");
        maxSendThread = (int)(long)sendThread.get("maxThread");
        coreSendThread = (int)(long)sendThread.get("coreThread");
        queueSendThread = (int)(long)sendThread.get("queue");

        JSONObject getThread = (JSONObject) systemParameters.get("getThread");
        maxGetThread = (int)(long)sendThread.get("maxThread");
        coreGetThread = (int)(long)sendThread.get("coreThread");
        queueGetThread = (int)(long)sendThread.get("queue");

        JSONObject connectCheck = (JSONObject) systemParameters.get("connectCheck");
        retryCount = (int)(long)connectCheck.get("retryCount");
        fixRate = (int)(long)connectCheck.get("fixRate");
    }

    public JSONObject getSystemParameters() {
        return systemParameters;
    }

    public int getTimeoutMessage() {
        return timeoutMessage;
    }

    public int getMaxSendThread() {
        return maxSendThread;
    }

    public int getCoreSendThread() {
        return coreSendThread;
    }

    public int getQueueSendThread() {
        return queueSendThread;
    }

    public int getMaxGetThread() {
        return maxGetThread;
    }

    public int getCoreGetThread() {
        return coreGetThread;
    }

    public int getQueueGetThread() {
        return queueGetThread;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public int getFixRate() {
        return fixRate;
    }

    public List<Issuer> getIssuerList() {
        return issuerList;
    }
}
