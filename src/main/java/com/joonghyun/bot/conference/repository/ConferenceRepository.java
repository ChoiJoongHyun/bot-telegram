package com.joonghyun.bot.conference.repository;

import com.joonghyun.model.Conference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by joonghyun on 2017. 5. 5..
 */
public interface ConferenceRepository extends JpaRepository<Conference, Integer> {
    Conference findByZone(Conference.Zone zone);

}
