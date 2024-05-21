package com.baro.portfolio.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Account {

    private Integer seq;
    private String nickname;

    public Account(Integer seq, String nickname) {
        this.seq = seq;
        this.nickname = nickname;
    }
}
