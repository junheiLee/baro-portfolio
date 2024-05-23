package com.baro.portfolio.web.dto;

import com.baro.portfolio.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class EditUserDto implements HavingUniqueField {

    @NotBlank
    @Size(min = 1, max = 20)
    private String nickname;

    @NotBlank
    @Size(min = 1, max = 20)
    private String currentNickname;

    @Size(max = 400)
    private String introduce;

    @Builder
    public EditUserDto(String nickname, String currentNickname, String introduce) {
        this.nickname = nickname;
        this.currentNickname = currentNickname;
        this.introduce = introduce;
    }

    public static EditUserDto fromEntity(User user) {

        return EditUserDto.builder()
                .nickname(user.getNickname())
                .currentNickname(user.getNickname())
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
