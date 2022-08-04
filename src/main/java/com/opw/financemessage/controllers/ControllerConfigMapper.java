package com.opw.financemessage.controllers;

import com.opw.financemessage.factory.SystemParameters;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
@RequestMapping("/manage")
@CrossOrigin
public class ControllerConfigMapper {

    @GetMapping("/configmapper/getfield")
    public JSONArray getField(){
        JSONParser parser = new JSONParser();
        try {
            FileReader fileReader = new FileReader("src/main/java/com/opw/financemessage/config/ConfigMapper.json");
            Object obj = parser.parse(fileReader);
            return (JSONArray) obj;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/configmapper/postfield")
    public void postField(@RequestBody String fieldList) throws Exception {
        fieldList = fieldList.substring(1, fieldList.length()-1).replace("\\", "");
        System.out.println(fieldList);

        FileWriter writer = new FileWriter("src/main/java/com/opw/financemessage/config/ConfigMapper.json");
        writer.write(fieldList);
        writer.flush();
    }

    @GetMapping("/socket/getsocket")
    public JSONObject getSocket(){
        SystemParameters systemParameters = new SystemParameters();
        return systemParameters.getSystemParameters();
    }

    @PostMapping("/socket/postsocket")
    public void postSocket(@RequestBody String socket) throws IOException {
        socket = socket.substring(1, socket.length()-1).replace("\\", "");
        System.out.println(socket);

        FileWriter writer = new FileWriter("src/main/resource/SystemParameter.json");
        writer.write(socket);
        writer.flush();
    }
}
