package com.baro.portfolio.repository.itf;

import com.baro.portfolio.domain.Project;

import java.util.Optional;

public interface ProjectRepository {

    int save(Project project);

    Optional<Project> findBySeq(Integer seq);

    int update(Integer seq, Project project);

    boolean remove(Integer seq);
}
