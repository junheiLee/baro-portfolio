package com.baro.portfolio.domain;

import lombok.Getter;

@Getter
public class Skill {

    private int seq;
    private String name;

    public Skill(String name) {
        this.name = name;
    }

    public Skill(int seq, String name) {
        this.seq = seq;
        this.name = name;
    }

}
