package com.baro.portfolio.repository.itf;

import com.baro.portfolio.domain.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {

    int save(int userSeq, Project project, String myPart);

    Optional<Project> findBySeq(int seq);

    int update(int seq, Project project);

    boolean remove(int seq);

    List<Integer> findContributorsBySeq(int seq);
}
