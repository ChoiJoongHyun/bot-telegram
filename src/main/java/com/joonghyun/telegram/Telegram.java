package com.joonghyun.telegram;

import com.joonghyun.telegram.polling.TelegramPolling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

/**
 * Created by joonghyun on 2017. 5. 3..
 */
@Configuration
public class Telegram {
    private static final Logger logger = LoggerFactory.getLogger(Telegram.class);

    @Bean
    TelegramPolling telegramSocket() {
        return new TelegramPolling();
    }

    @PostConstruct
    void telegramInitializer() {
        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(telegramSocket());
        } catch (TelegramApiException e) {
            // 봇 네임, 토큰을 잘못 입력하였을경우.
            logger.error("telegramInitializerException - {}", e);
        }
    }
}
