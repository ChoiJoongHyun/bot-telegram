package com.joonghyun.telegram.polling;

import com.joonghyun.dispatch.MessageDispatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by joonghyun on 2017. 5. 3..
 */
public class TelegramPolling extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(TelegramPolling.class);

    @Autowired
    private MessageDispatch messageDispatch;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            try {
                logger.info("update toString : " + update.toString());

                String resultMsg = messageDispatch.message(update.getMessage().getChatId(), update.getMessage().getText());

                if(resultMsg != null) {
                    logger.info("resultMsg : {}", resultMsg);

                    SendMessage message = new SendMessage() // Create a SendMessage
                            // object with mandatory
                            // fields
                            //.setChatId(update.getMessage().getChatId()).setText(update.getMessage().getText());
                            .setChatId(update.getMessage().getChatId()).setText(resultMsg);
                    sendMessage(message);
                }
            } catch (TelegramApiException e) {
                logger.error("TelegramApiException - {}", e);
            }
        }
    }

    @Override
    public String getBotUsername() {
        //return "UltraDemoBot";
        return "iparking_bot";
        //{"ok":true,"result":{"id":292023131,"first_name":"iParkingBot","username":"iparking_bot"}}
    }

    @Override
    public String getBotToken() {
        //return "303523137:AAG4OXaFUdB91Jde5u__e4WRC2LfDyre7n4";
        return "292023131:AAFR9TRGMchV4Jsq6pZPL6L7a8cSnK0VyHc";
        //return "bot292023131:AAFR9TRGMchV4Jsq6pZPL6L7a8cSnK0VyHc";
    }
}
