package com.baro.portfolio.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class Account {

    private int seq;
    private String nickname;

    public Account(int seq, String nickname) {
        this.seq = seq;
        this.nickname = nickname;
    }
}
