package com.joonghyun.bot.conference.model;

import com.joonghyun.model.Conference;
import com.joonghyun.model.ConferenceReserve;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by joonghyun on 2017. 5. 3..
 */
public class ConferenceReserveDto {

    private String zone;

    private String date;

    private String timeZone;

    private String content;

    private String cancelName;

    private String cancelReason;

    private String reserveName;

    private boolean delete;

    public ConferenceReserveDto(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date currentTime_1 = new Date(System.currentTimeMillis());
        this.date = formatter.format(currentTime_1);
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
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

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
