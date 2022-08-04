package com.opw.financemessage.config;


import com.opw.financemessage.mapper.MapperDataElement;
import com.opw.financemessage.models.DataElement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Configuration
public class ConfigMapper {

    @SuppressWarnings("unchecked")
    @Bean
    public MapperDataElement testConfigMapper(){
        MapperDataElement m = new MapperDataElement();
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("src/main/java/com/opw/financemessage/config/ConfigMapper.json")){
            Object obj = parser.parse(reader);

            JSONArray dataElementList = (JSONArray) obj;

            dataElementList.forEach(Elemnent -> {
                JSONObject emp = (JSONObject) Elemnent;
                DataElement dataElemnt = new DataElement();

                int id = Math.toIntExact((Long) emp.get("id"));

                JSONObject DataElement = (JSONObject) emp.get("dataElement");

                dataElemnt.setNumber(Math.toIntExact((Long) DataElement.get("number")));

                String lengthType = (String) DataElement.get("lengthType");
                dataElemnt.setLengthType(lengthType);

                if (lengthType.equals("VARIABLE")){
                    dataElemnt.setPrefixLength(Math.toIntExact((Long) DataElement.get("prefixLength")));
                }else{
                    dataElemnt.setLength(Math.toIntExact((Long) DataElement.get("length")));
                }
                dataElemnt.setType((String) DataElement.get("type"));
                m.getDataElement().put(id,dataElemnt);
            });
            System.out.print(m.getDataElement().get(2).getNumber()+" ");
            System.out.print(m.getDataElement().get(2).getLength()+" ");
            System.out.print(m.getDataElement().get(2).getPrefixLength()+" ");
            System.out.print(m.getDataElement().get(2).getLengthType()+" ");
            System.out.print(m.getDataElement().get(2).getType());
            System.out.println();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return m;
    }

//    public static void main(String[] args) {
//        TestConfig TC = new TestConfig();
//        TC.configMaper();
//    }
}
