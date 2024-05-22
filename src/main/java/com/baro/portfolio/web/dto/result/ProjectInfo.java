package com.baro.portfolio.web.dto.result;

import com.baro.portfolio.domain.Project;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class ProjectInfo {

    private int seq;
    private String title;
    private String description;
    private boolean isPublic;
    private Date start;
    private Date end;
    private boolean isProceeding;
    private Integer headcount;
    private String mainFunction;
    private String interest;
    private String github;
    private List<Integer> contributors;

    @Builder
    public ProjectInfo(int seq, String title, String description, boolean isPublic,
                       Date start, Date end, boolean isProceeding, Integer headcount,
                       String mainFunction, String interest, String github) {
        this.seq = seq;
        this.title = title;
        this.description = description;
        this.isPublic = isPublic;
        this.start = start;
        this.end = end;
        this.isProceeding = isProceeding;
        this.headcount = headcount;
        this.mainFunction = mainFunction;
        this.interest = interest;
        this.github = github;
    }

    public static ProjectInfo fromEntity(Project project) {

        return ProjectInfo.builder()
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
                .build();

    }

    public void addContributors(List<Integer> newContributors) {

        this.contributors = new ArrayList<>(newContributors);
    }

}
