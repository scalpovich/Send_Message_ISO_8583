package com.opw.financemessage.repository;

import com.opw.financemessage.entity.OnlineLog;
import com.opw.financemessage.entity.TransLog;
import com.opw.financemessage.models.DataReceive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface TransLogRepository extends JpaRepository<TransLog, Long> {
    default long addTransLog(Map<Integer, String> mapData) {
        TransLog transLog = this.save(new TransLog());

        transLog.setMTI(mapData.get(0));
        if(mapData.containsKey(2))
            transLog.setF2(mapData.get(2));

        if(mapData.containsKey(3))
            transLog.setF3(mapData.get(3));

        if(mapData.containsKey(4))
            transLog.setF4(mapData.get(4));

        if(mapData.containsKey(49))
            transLog.setF49(mapData.get(49));

        if(mapData.containsKey(5))
            transLog.setF5(mapData.get(5));

        if(mapData.containsKey(50))
            transLog.setF50(mapData.get(50));

        if(mapData.containsKey(9))
            transLog.setF9(mapData.get(9));

        if(mapData.containsKey(6))
            transLog.setF6(mapData.get(6));

        if(mapData.containsKey(51))
            transLog.setF51(mapData.get(51));

        if(mapData.containsKey(10))
            transLog.setF10(mapData.get(10));

        if(mapData.containsKey(11))
            transLog.setF11(mapData.get(11));

        if(mapData.containsKey(12))
            transLog.setF12(mapData.get(12));

        if(mapData.containsKey(13))
            transLog.setF13(mapData.get(13));

        if(mapData.containsKey(15))
            transLog.setF15(mapData.get(15));

        if(mapData.containsKey(18))
            transLog.setF18(mapData.get(18));

        if(mapData.containsKey(22))
            transLog.setF22(mapData.get(22));

        if(mapData.containsKey(25))
            transLog.setF25(mapData.get(25));

        if(mapData.containsKey(41))
            transLog.setF41(mapData.get(41));

        if(mapData.containsKey(102))
            transLog.setF102(mapData.get(102));

        if(mapData.containsKey(103))
            transLog.setF103(mapData.get(103));

        if(mapData.containsKey(37))
            transLog.setF37(mapData.get(37));

        if(mapData.containsKey(38))
            transLog.setF38(mapData.get(38));

        transLog.setSYS_DATE(new java.sql.Date(Calendar.getInstance().getTime().getTime()));

        save(transLog);

        return transLog.getID();
    }
}
