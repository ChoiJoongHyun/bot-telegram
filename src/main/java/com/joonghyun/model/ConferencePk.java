package com.joonghyun.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

/**
 * Created by joonghyun on 2017. 5. 3..
 */
@Embeddable
public class ConferencePk {
    @Column(name="date", nullable=false)
    private String date;
    @Column(name="timea", nullable=false)
    private String timea;
}
