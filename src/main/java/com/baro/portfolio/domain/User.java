package com.baro.portfolio.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class User {

    private Integer seq;
    private String email;
    private String password;
    private String nickname;
    private String name;
    private String phone;
    private String image;
    private String introduce;

    @Builder
    public User(Integer seq, String email, String password, String nickname, String name, String phone, String image, String introduce) {
        this.seq = seq;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.phone = phone;
        this.image = image;
        this.introduce = introduce;
    }
}
