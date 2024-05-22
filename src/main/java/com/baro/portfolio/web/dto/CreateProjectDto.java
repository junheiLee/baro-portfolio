package com.baro.portfolio.web.dto;

import com.baro.portfolio.domain.Project;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import java.sql.Date;


@Slf4j
@ToString
@Getter
@Setter
public class CreateProjectDto extends ProjectDateDto implements Unique {

    @NotBlank
    @Length(min = 1, max = 20, message = "1 ~ 20자로 작성해주세요.")
    private String title;

    @Length(max = 400, message = "400자 이내로 요약해주세요.")
    private String description;

    private Boolean isPublic;

    private Boolean isProceeding;

    @NotNull(message = "참여 인원은 필수 값입니다.")
    @Range(min = 1, max = 100, message = "1 ~ 100명 사이의 값을 입력해주세요.")
    private Integer headcount;

    @Length(max = 1000, message = "주요 기능은 1000자 이내로 작성해 주세요.")
    private String mainFunction;

    @Length(max = 1000, message = "관심사는 1000자 이내로 작성해주세요.")
    private String interest;

    @URL(protocol = "https", host = "github.com", message = "깃허브 주소를입력해 주세요")
    @Length(max = 500, message = "github 링크는 500자 이내로 작성해주세요.")
    private String github;

    @Length(max = 1000, message = "개인 기여는 1000자 이내로 작성해주세요.")
    private String myPart;

    public CreateProjectDto() {

    }

    public Project toProjectEntity() {

        return Project.builder().title(title)
                .description(description)
                .isPublic(isPublic ? 1 : 0)
                .start(start).end(end)
                .isProceeding(isProceeding ? 1 : 0)
                .headcount(headcount)
//                .architecture(architecture)
//                .erd(erd)
                .mainFunction(mainFunction)
                .interest(interest)
                .github(github)
                .build();
    }

    public CreateProjectDto editIsProceeding() {
        Date current = new Date(System.currentTimeMillis());

        if (isProceeding && end.before(current)) {
            isProceeding = false;
        } else if (!isProceeding && end.after(current)) {
            isProceeding = true;
        }
        return this;
    }
}
