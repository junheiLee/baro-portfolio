package com.baro.portfolio.web.dto;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class EditUserDto {

    private String nickname;
    private String image;
    private String introduce;

    @Builder
    public EditUserDto(String nickname, String image, String introduce) {
        this.nickname = nickname;
        this.image = image;
        this.introduce = introduce;
    }
}
