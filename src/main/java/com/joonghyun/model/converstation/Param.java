package com.joonghyun.model.converstation;

/**
 * Created by joonghyun on 2017. 5. 4..
 */
public class Param {
    private String key;

    private String value;

    public Param(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
