package com.baro.portfolio.repository.itf;

import com.baro.portfolio.domain.PortfolioProject;
import com.baro.portfolio.domain.Project;
import com.baro.portfolio.domain.User;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {

    List<Project> findByUserSeqAndIsPublic(Integer userSeq, Integer isPublic);

    List<PortfolioProject> findPortfolioProjects(int userSeq);

    int save(int userSeq, Project project, String myPart);

    Optional<Project> findBySeq(int seq);

    int update(int projectSeq, Project project, int userSeq, String myPart);

    boolean removeProject(int seq);

    List<User> findContributorsBySeq(int seq);

    String findMyPart(int userSeq, int projectSeq);

    void removeContributor(int userSeq, int projectSeq);

    int countContributors(int projectSeq);
}
