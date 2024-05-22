package com.baro.portfolio.repository.itf;

import com.baro.portfolio.domain.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {

    List<Project> findByUserSeqAndIsPublic(Integer userSeq, Integer isPublic);

    int save(int userSeq, Project project, String myPart);

    Optional<Project> findBySeq(int seq);

    int update(int projectSeq, Project project, int userSeq, String myPart);

    boolean removeProject(int seq);

    List<Integer> findContributorsBySeq(int seq);

    String findMyPart(int userSeq, int projectSeq);

    void removeContributor(int userSeq, int projectSeq);

    int countContributors(int projectSeq);
}
