package com.joonghyun.bot.conference.repository;

import com.joonghyun.model.Conference;
import com.joonghyun.model.ConferenceReserve;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by joonghyun on 2017. 5. 5..
 */
public interface ConferenceReserveRepository extends JpaRepository<ConferenceReserve, Integer> {
    List<ConferenceReserve> findAllByDateEqualsOrderByTimeZone(String date);
    //List<ConferenceReserve> findAllByDateEqualsAndTimeZoneEqualsOrderByTimeZone(String date, String zone);
    List<ConferenceReserve> findAllByDateAndConferenceOrderByTimeZone(String date, Conference conference);
    List<ConferenceReserve> findAllByTimeZone(ConferenceReserve.TimeZone timeZone);

}
