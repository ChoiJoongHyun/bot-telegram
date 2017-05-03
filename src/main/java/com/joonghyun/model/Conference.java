package com.joonghyun.model;


import javax.persistence.*;

/**
 * Created by joonghyun on 2017. 5. 3..
 */
@Entity
public class Conference {
    @Id
    @GeneratedValue
    private Integer idx;

    @Column(name="date", nullable=false)
    private String date;

    @Column(name="time", nullable=false)
    @Enumerated(EnumType.STRING)
    private TimeZone time;

    @Column(name="content", nullable=false)
    private String content;

    @Column(name="reserve_name", nullable=false)
    private String reserveName;

    @Column(name="cancel_reason")
    private String cancelReason;

    @Column(name="cancel_name")
    private String cancelName;

    @Column(name="delete")
    private Boolean delete = false;

    public enum TimeZone {
        T9("9시 ~ 10시")
        , T10("10시 ~ 11시");

        private String descript;

        TimeZone(String descript) {
            this.descript = descript;
        }

        public String getDescript() {
            return descript;
        }
    }

//    @EmbeddedId
//    private Conference.PK pk;
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

}
