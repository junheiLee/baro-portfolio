package com.baro.portfolio.web.dto;

import com.baro.portfolio.domain.Project;
import com.baro.portfolio.web.dto.result.ProjectInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ToString
@NoArgsConstructor
@Getter
@Setter
public class EditProjectDto extends ProjectDateDto {

    @NotBlank(message = "제목은 공백이 아닌 문자로 입력해주세요.")
    @Length(min = 1, max = 20, message = "1 ~ 20자로 작성해주세요.")
    private String title;

    @Length(max = 400, message = "400자 이내로 요약해주세요.")
    private String description;

    private Boolean isPublic;

    private Boolean isProceeding;

    @NotNull(message = "참여 인원은 필수 값입니다.")
    @Range(min = 1, max = 100, message = "1 ~ 100명 사이의 값을 입력해주세요.")
    private Integer headcount;

//    @Size(max = 500)
//    private String architecture;
//
//    @Size(max = 500)
//    private String erd;

    @Length(max = 1000, message = "주요 기능은 1000자 이내로 작성해 주세요.")
    private String mainFunction;

    @Length(max = 1000, message = "관심사는 1000자 이내로 작성해주세요.")
    private String interest;

    @URL(protocol = "https", host = "github.com", message = "깃허브 주소를입력해 주세요")
    @Length(max = 500, message = "github 링크는 500자 이내로 작성해주세요.")
    private String github;

    @Length(max = 1000, message = "개인 기여는 1000자 이내로 작성해주세요.")
    private String myPart;

    private List<Integer> contributors;


    public Project toProjectEntity() {
        log.info("EditProjectDto toProjectEntity 호출, isPublic={}", isPublic);
        return Project.builder()
                .title(title)
                .description(description)
                .isPublic(isPublic ? 1 : 0)
                .start(super.start).end(super.end)
                .isProceeding(isProceeding ? 1 : 0)
                .headcount(headcount)
//                .architecture(architecture)
//                .erd(erd)
                .mainFunction(mainFunction)
                .interest(interest)
                .github(github)
                .build();
    }

    public void fromProjectInfo(ProjectInfo projectInfo) {

        this.title = projectInfo.getTitle();
        this.description = projectInfo.getDescription();
        this.isPublic = projectInfo.isPublic();
        super.start = projectInfo.getStart();
        this.end = projectInfo.getEnd();
        this.isProceeding = projectInfo.isProceeding();
        this.headcount = projectInfo.getHeadcount();
        this.mainFunction = projectInfo.getMainFunction();
        this.interest = projectInfo.getInterest();
        this.github = projectInfo.getGithub();

        contributors = new ArrayList<>(projectInfo.getContributors());
    }

    public EditProjectDto editIsProceeding() {
        Date current = new Date(System.currentTimeMillis());

        if (isProceeding && end.before(current)) {
            isProceeding = false;
        } else if (!isProceeding && end.after(current)) {
            isProceeding = true;
        }
        return this;
    }

    public EditProjectDto setMyPart(String myPart) {
        this.myPart = myPart;
        return this;
    }
}
