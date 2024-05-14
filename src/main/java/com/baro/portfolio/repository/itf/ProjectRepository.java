package com.baro.portfolio.repository.itf;

import com.baro.portfolio.domain.Project;

import java.util.Optional;

public interface ProjectRepository {

    void save(Project project);

    Optional<Project> findBySeq(Integer seq);

    void update(Integer seq, Project project);

    boolean remove(Integer seq);
}
