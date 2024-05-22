package com.baro.portfolio.repository.itf;

import com.baro.portfolio.domain.PortfolioProject;
import com.baro.portfolio.domain.Project;
import com.baro.portfolio.domain.User;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {

    List<PortfolioProject> findPortfolioProjects(int userSeq);

    List<Project> findByUserSeqAndIsPublic(Integer userSeq, Integer isPublic);

    int save(int userSeq, Project project, String myPart);

    Optional<Project> findBySeq(int projectSeq);

    String findMyPart(int userSeq, int projectSeq);

    List<User> findContributorsBySeq(int projectSeq);

    int update(int projectSeq, Project project, int userSeq, String myPart);

    void removeContributor(int userSeq, int projectSeq);

    int countContributors(int projectSeq);

    boolean removeProject(int projectSeq);

}
