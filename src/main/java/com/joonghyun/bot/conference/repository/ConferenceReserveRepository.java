package com.joonghyun.bot.conference.repository;

import com.joonghyun.model.Conference;
import com.joonghyun.model.ConferenceReserve;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by joonghyun on 2017. 5. 5..
 */
public interface ConferenceReserveRepository extends JpaRepository<ConferenceReserve, Integer> {

    List<ConferenceReserve> findAllByDateAndConferenceOrderByTimeZone(String date, Conference conference);

    ConferenceReserve findAllByDateAndConferenceAndTimeZone(String date, Conference conference, ConferenceReserve.TimeZone timeZone);

}
