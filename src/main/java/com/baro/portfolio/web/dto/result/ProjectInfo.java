package com.baro.portfolio.web.dto.result;

import com.baro.portfolio.domain.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class ProjectInfo {

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

    public void fromEntity(Project project) {

        this.title = project.getTitle();
        this.description = project.getDescription();
        this.isPublic = project.getIsPublic() == 1;
        this.start = project.getStart();
        this.end = project.getEnd();
        this.isProceeding = project.getIsProceeding() == 1;
        this.headcount = project.getHeadcount();
        this.mainFunction = project.getMainFunction();
        this.interest = project.getInterest();
        this.github = project.getGithub();

    }

    public void addContributors(List<Integer> newContributors) {

        this.contributors = new ArrayList<>(newContributors);
    }

}
