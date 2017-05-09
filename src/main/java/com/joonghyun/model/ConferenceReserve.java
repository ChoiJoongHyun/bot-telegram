package com.joonghyun.model;


import javax.persistence.*;

/**
 * Created by joonghyun on 2017. 5. 3..
 */
@Entity

public class ConferenceReserve {
    @Id
    @GeneratedValue
    private Integer idx;

    @ManyToOne
    @JoinColumn(foreignKey=@ForeignKey(name="fk_reserve_conference"))
    private Conference conference;

    @Column(name="date", nullable=false)
    private String date;

    @Column(name="time_zone", nullable=false)
    @Enumerated(EnumType.STRING)
    private TimeZone timeZone;

    @Column(name="content", nullable=false)
    private String content;

    @Column(name="reserve_name", nullable=false)
    private String reserveName;

    @Column(name="cancel_reason")
    private String cancelReason;

    @Column(name="cancel_name")
    private String cancelName;

    @Column(name="delete", columnDefinition = "boolean default false")
    private Boolean delete;

    public enum TimeZone {
        T9("9시 ~ 10시")
        , T10("10시 ~ 11시")
        , T11("11시 ~ 12시")
        , T12("12시 ~ 13시")
        , T13("13시 ~ 14시")
        , T14("14시 ~ 15시")
        , T15("15시 ~ 16시")
        , T16("16시 ~ 17시")
        , T17("17시 ~ 18시");

        private String descript;

        TimeZone(String descript) {
            this.descript = descript;
        }

        public String getDescript() {
            return descript;
        }
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public String toShortString() {
        return "[" + timeZone.name() + "] " + timeZone.getDescript() + " : " +  reserveName + "(" + content + ")";
    }

    @Override
    public String toString() {
        return "ConferenceReserve{" +
                "idx=" + idx +
                ", conference=" + conference +
                ", date='" + date + '\'' +
                ", timeZone=" + timeZone +
                ", content='" + content + '\'' +
                ", reserveName='" + reserveName + '\'' +
                ", cancelReason='" + cancelReason + '\'' +
                ", cancelName='" + cancelName + '\'' +
                ", delete=" + delete +
                '}';
    }

    //    @EmbeddedId
//    private ConferenceReserve.PK pk;
//    @Embeddable
//    public class PK implements Serializable {
//        private String date;
//        private String time;
//
//        public PK() {
//            super();
//        }
//
//        public String getDate() {
//            return date;
//        }
//
//        public void setDate(String date) {
//            this.date = date;
//        }
//
//        public String getTime() {
//            return time;
//        }
//
//        public void setTime(String time) {
//            this.time = time;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (o == this) {
//                return true;
//            }
//            if ( ! (o instanceof PK)) {
//                return false;
//            }
//            PK other = (PK) o;
//            return this.date.equals(other.date)
//                    && this.time.equals(other.time);
//        }
//
//        @Override
//        public int hashCode() {
//            return this.date.hashCode()
//                    ^ this.time.hashCode();
//        }
//    }


    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReserveName() {
        return reserveName;
    }

    public void setReserveName(String reserveName) {
        this.reserveName = reserveName;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getCancelName() {
        return cancelName;
    }

    public void setCancelName(String cancelName) {
        this.cancelName = cancelName;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }
}
