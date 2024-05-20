package com.baro.portfolio.web.dto.result;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AccountInfo {

    private Integer seq;
    private String nickname;

    public AccountInfo(Integer seq, String nickname) {
        this.seq = seq;
        this.nickname = nickname;
    }
}
