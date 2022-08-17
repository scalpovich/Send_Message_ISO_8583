package com.opw.financemessage.repository;

import com.opw.financemessage.entity.OnlineLog;
import com.opw.financemessage.entity.TransLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Calendar;

@Repository
public interface OnlineLogRepository extends JpaRepository<OnlineLog, Long> {
    default void addOnlineLog(String field63, boolean o, String mti, String rawMessage, String field39){
        OnlineLog onlineLog = this.save(new OnlineLog());
        onlineLog.setEXT_KEY(field63);
        onlineLog.setINT_KEY(field39);
        onlineLog.setDIRECTION(o?"O":"I");
//        onlineLog.setBUF1(rawMessage);
        onlineLog.setMTID(mti);
        onlineLog.setREC_DATE(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        if(field39 != null)
            onlineLog.setRC(field39);
        save(onlineLog);
    }
}
