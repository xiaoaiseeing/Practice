package com.tplink.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author ZouYun
 * @version 1.0
 * @since 2020/8/25 10:16
 */
@Entity
public class LoginNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private long time;

    public LoginNumber(String name, long time) {
        this.name = name;
        this.time = time;
    }

    public LoginNumber() {}

    public long getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }
}
