package com.opw.financemessage.controllers;


import com.opw.financemessage.entity.CardInfor;
import com.opw.financemessage.models.DataReceive;
import com.opw.financemessage.repository.CardInfoRepository;
import com.opw.financemessage.services.Impl.MessageBatchTransaction;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/batchmessage")
public class ControllerBatchTransaction {

    @Autowired
    private CardInfoRepository cardInfoRepository;

    private MessageBatchTransaction messageBatchTransaction;

    @Autowired
    public ControllerBatchTransaction(MessageBatchTransaction messageBatchTransaction){
        this.messageBatchTransaction = messageBatchTransaction;
    }
    @PostMapping("/post")
    public String sendMessage(@RequestBody List<DataReceive> data) throws Exception {
        int numberTransaction = Integer.parseInt(data.get(0).getValue());
        int transaction = Integer.parseInt(data.get(1).getValue());
        String contentTransaction = data.get(2).getValue();
        FileWriter fileWriter;
        if (transaction == 1) {
            fileWriter = new FileWriter("src/main/resources/transactionConfig/BalanceConfig.json");
        } else if (transaction == 2) {
            fileWriter = new FileWriter("src/main/resources/transactionConfig/PurchaseConfig.json");
        } else if (transaction == 3) {
            fileWriter = new FileWriter("src/main/resources/transactionConfig/WithdrawConfig.json");
        } else if (transaction == 4) {
            fileWriter = new FileWriter("src/main/resources/transactionConfig/TransferConfig.json");
        } else if (transaction == 5) {
            fileWriter = new FileWriter("src/main/resources/transactionConfig/ChangePINConfig.json");
        } else {
            fileWriter = new FileWriter("src/main/resources/transactionConfig/Statement.json");
        }
        fileWriter.write(contentTransaction);
        fileWriter.flush();
        List<CardInfor> listCardInfor = cardInfoRepository.findAll();
        List<CompletableFuture<String>> response = new ArrayList<>();
        for (int i = 0; i < numberTransaction ; i++){
            CardInfor cardInfor = listCardInfor.get((int)(Math.random()*10));
            response.add(messageBatchTransaction.send(transaction,contentTransaction,cardInfor));
        }
//        Thread.sleep(3000);

//        messageBatchTransaction.get(numberTransaction);

//        messageBatchTransaction.get(numberTransaction);
        if (response.size() == numberTransaction){
            return "{\"message\" : \"Done\"}";
        }
//        CompletableFuture<String> res = messageBatchTransaction.send(transaction, contentTransaction, listCardInfor.get(0));
//        CompletableFuture.allOf(res).join();
        return "{\"message\" : \"false\"}";
    }

    @PostMapping("/gettransaction")
    public JSONArray getTransaction(@RequestBody int transaction) throws Exception {
        JSONParser jsonParser = new JSONParser();
        FileReader reader ;
        if (transaction == 1){
            reader = new FileReader("src/main/resources/transactionConfig/BalanceConfig.json");
        } else if (transaction == 2) {
            reader = new FileReader("src/main/resources/transactionConfig/PurchaseConfig.json");
        }else if (transaction == 3){
            reader = new FileReader("src/main/resources/transactionConfig/WithdrawConfig.json");
        }else if (transaction == 4){
            reader = new FileReader("src/main/resources/transactionConfig/TransferConfig.json");
        }else if (transaction == 5){
            reader = new FileReader("src/main/resources/transactionConfig/ChangePINConfig.json");
        }else{
            reader = new FileReader("src/main/resources/transactionConfig/Statement.json");
        }
        Object obj = jsonParser.parse(reader);
        JSONArray fieldList = (JSONArray) obj;
//        System.out.println(fieldList);
        return fieldList;
    }
}
