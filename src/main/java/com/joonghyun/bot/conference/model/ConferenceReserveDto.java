package com.joonghyun.bot.conference.model;

import com.joonghyun.bot.conference.service.ConferenceService;
import com.joonghyun.error.UserHandlerException;
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

    public Conference.Zone getZone() {
        try {
            return Conference.Zone.valueOf(zone.toUpperCase().trim());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new UserHandlerException(ConferenceService.ErrorCode.NO_EXIST_CONFERENCE);
        }
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

    public ConferenceReserve.TimeZone getTimeZone() {
        try {
            return ConferenceReserve.TimeZone.valueOf(timeZone.toUpperCase().trim());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new UserHandlerException(ConferenceService.ErrorCode.NO_EXIST_TIMEZONE);
        }
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
