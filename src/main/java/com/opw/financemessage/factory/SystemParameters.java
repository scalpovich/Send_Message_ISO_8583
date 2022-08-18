package com.opw.financemessage.factory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SystemParameters {
    private JSONObject systemParameters;

    public SystemParameters() {
        JSONParser jsonParser = new JSONParser();
        File file = new File("SystemParameters.java");
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
    }

    public JSONObject getSystemParameters() {
        return systemParameters;
    }

    public void setSystemParameters(JSONObject systemParameters) {
        this.systemParameters = systemParameters;
    }
}
