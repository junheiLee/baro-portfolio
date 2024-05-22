package com.baro.portfolio.web.dto;

import com.baro.portfolio.domain.User;
import lombok.*;

@ToString
@Getter @Setter
@NoArgsConstructor
public class EditUserDto implements Unique{

    private String nickname;
    private String introduce;

    @Builder
    public EditUserDto(String nickname,String introduce) {
        this.nickname = nickname;
        this.introduce = introduce;
    }

    public static EditUserDto fromEntity(User user){

        return EditUserDto.builder()
                .nickname(user.getNickname())
                .introduce(user.getIntroduce())
                .build();
    }

    public User toEntity() {
        return User.builder()
                .nickname(nickname)
                .introduce(introduce)
                .build();
    }
}
