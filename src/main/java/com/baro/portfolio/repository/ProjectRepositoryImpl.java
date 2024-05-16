package com.baro.portfolio.repository;

import com.baro.portfolio.domain.Project;
import com.baro.portfolio.repository.itf.ProjectRepository;
import com.baro.portfolio.repository.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {

    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectRepositoryImpl(ProjectMapper projectMapper) {

        this.projectMapper = projectMapper;
    }

    @Override
    public void save(Project project) {

        this.projectMapper.save(project);
    }

    @Override
        public Optional<Project> findBySeq(Integer seq) {

        return this.projectMapper.findBySeq(seq);
    }

    @Override
    public Integer update(Integer seq, Project project) {

        return this.projectMapper.update(seq, project);
    }

    @Override
    public boolean remove(Integer seq) {
        return false;
    }
}
