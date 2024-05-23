package com.baro.portfolio.web.dto.result;

import com.baro.portfolio.domain.PortfolioProject;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
public class PortfolioProjectInfo extends ProjectInfo {

    private String myPart;

    @Builder(builderMethodName = "builderForPortFolio")
    public PortfolioProjectInfo(int seq, String title, String description,
                                boolean isPublic, Date start, Date end,
                                boolean isProceeding, Integer headcount,
                                String mainFunction, String interest,
                                String github, String myPart) {
        super(seq, title, description, isPublic, start, end, isProceeding, headcount, mainFunction, interest, github);
        this.myPart = myPart;
    }

    public static PortfolioProjectInfo fromEntity(PortfolioProject project) {

        return PortfolioProjectInfo.builderForPortFolio()
                .seq(project.getSeq())
                .title(project.getTitle())
                .description(project.getDescription())
                .isPublic(project.getIsPublic() == 1)
                .start(project.getStart())
                .end(project.getEnd())
                .isProceeding(project.getIsProceeding() == 1)
                .headcount(project.getHeadcount())
                .mainFunction(project.getMainFunction())
                .interest(project.getInterest())
                .github(project.getGithub())
                .myPart(project.getMyPart())
                .build();

    }
}
