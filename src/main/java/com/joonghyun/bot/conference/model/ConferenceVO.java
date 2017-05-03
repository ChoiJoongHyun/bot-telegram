package com.joonghyun.bot.conference.model;

import com.joonghyun.model.Conference;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by joonghyun on 2017. 5. 3..
 */
public class ConferenceVO {

    private Conference.Zone zone;

    private String date;

    private Conference.TimeZone timeZone;

    private String content;

    private String cancelName;

    private String cancelReason;

    private String reserveName;

    private Boolean delete;

    public ConferenceVO (){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date currentTime_1 = new Date(System.currentTimeMillis());
        this.date = formatter.format(currentTime_1);
    }

    public Conference.Zone getZone() {
        return zone;
    }

    public void setZone(Conference.Zone zone) {
        this.zone = zone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Conference.TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(Conference.TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCancelName() {
        return cancelName;
    }

    public void setCancelName(String cancelName) {
        this.cancelName = cancelName;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getReserveName() {
        return reserveName;
    }

    public void setReserveName(String reserveName) {
        this.reserveName = reserveName;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }
}
