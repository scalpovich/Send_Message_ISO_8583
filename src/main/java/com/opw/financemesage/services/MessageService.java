package com.opw.financemesage.services;

import com.opw.financemesage.models.MessageISO;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Service;

import java.io.IOException;

public interface MessageService {
    MessageISO sendMessage(MessageISO message);
}
