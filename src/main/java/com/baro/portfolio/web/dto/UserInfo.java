package com.baro.portfolio.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserInfo {

    private int seq;
    private String name;
    private String nickname;
    private String email;
    private String introduce;

    @Builder
    public UserInfo(int seq, String name, String nickname, String email, String introduce) {
        this.seq = seq;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.introduce = introduce;
    }
}
