package com.opw.financemessage.controllers;

import com.opw.financemessage.factory.SystemParameters;
import com.opw.financemessage.mapper.MapperDataElement;
import com.opw.financemessage.socket.SocketIO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
@RequestMapping("/manage")
@CrossOrigin
public class ControllerConfigMapper {

    @Autowired
    SocketIO socketIO;
    @Autowired
    MapperDataElement mapperDataElement;

    @GetMapping("/configmapper/getfield")
    public JSONArray getField(){
        JSONParser parser = new JSONParser();
        try {
            FileReader fileReader = new FileReader("src/main/resources/transactionConfig/ConfigMapper.json");
            JSONArray obj = (JSONArray) parser.parse(fileReader);
            return obj;
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
//        fieldList = fieldList.substring(1, fieldList.length()-1).replace("\\", "");
        System.out.println(fieldList);

        FileWriter writer = new FileWriter("src/main/resources/transactionConfig/ConfigMapper.json");
        writer.write(fieldList);
        writer.flush();
        mapperDataElement.loadDataElement();
    }

    @GetMapping("/socket/getsocket")
    public JSONObject getSocket(){
        SystemParameters systemParameters = new SystemParameters();
        return systemParameters.getSystemParameters();
    }

    @PostMapping("/socket/postsocket")
    public void postSocket(@RequestBody String socket) throws IOException {
//        socket = socket.substring(1, socket.length()-1).replace("\\", "");
        System.out.println(socket);

        FileWriter writer = new FileWriter("src/main/resources/SystemParameter.json");
        writer.write(socket);
        writer.flush();
        socketIO.setConnected(false);
    }

//    @PostMapping("/switch")
//    public void postSwitch (@RequestBody boolean connect){
//        if (connect) {
//            socketIO.connect();
//            System.out.println("connected");
//        }
//        else {
//            System.out.println("disconnected");
//            socketIO.disConnect();
//        }
//    }
}
