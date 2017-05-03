package com.joonghyun.telegram.polling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by joonghyun on 2017. 5. 3..
 */
public class TelegramPolling extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(TelegramPolling.class);

    //@Autowired
    //private Gateway gateway;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            try {
                logger.info("update toString : " + update.toString());
                SendMessage message = new SendMessage() // Create a SendMessage
                        // object with mandatory
                        // fields
                        .setChatId(update.getMessage().getChatId()).setText(update.getMessage().getText());
                sendMessage(message);
            } catch (TelegramApiException e) {
                logger.error("TelegramApiException - {}", e);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "UltraDemoBot";
    }

    @Override
    public String getBotToken() {
        return "303523137:AAG4OXaFUdB91Jde5u__e4WRC2LfDyre7n4";
    }
}
