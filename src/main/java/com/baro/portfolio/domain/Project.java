package com.baro.portfolio.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@Getter
public class Project {

    private Integer seq;
    private String title;
    private String description;
    private Integer isPublic;
    private Date start;
    private Date end;
    private Integer isProceeding;
    private Integer headcount;
    private String architecture;
    private String erd;
    private String mainFunction;
    private String interest;
    private String github;

    @Builder
    public Project(String title, Integer isPublic,
                   Date start, Date end, Integer isProceeding, Integer headcount,
                   String description, String architecture, String erd,
                   String mainFunction, String interest, String github) {
        this.title = title;
        this.isPublic = isPublic;
        this.start = start;
        this.end = end;
        this.isProceeding = isProceeding;
        this.headcount = headcount;
        this.description = description;
        this.architecture = architecture;
        this.erd = erd;
        this.mainFunction = mainFunction;
        this.interest = interest;
        this.github = github;
    }

}
