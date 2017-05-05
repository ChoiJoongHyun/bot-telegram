package com.joonghyun.model;

import javax.persistence.*;

/**
 * Created by joonghyun on 2017. 5. 5..
 */
@Entity
public class Conference {

    @Id
    @GeneratedValue
    private Integer idx;

    @Column(name="zone", nullable=false)
    @Enumerated(EnumType.STRING)
    private Zone zone;

    @Column
    private String description;

    public enum Zone {
        C601("6층 회의실 601")
        , C602("6층 회의실 602")
        , C702("7층 회의실 701");

        private String descript;

        Zone(String descript) {
            this.descript = descript;
        }

        public String getDescript() {
            return descript;
        }
    }

}
