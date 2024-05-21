package com.baro.portfolio.repository;

import com.baro.portfolio.domain.Project;
import com.baro.portfolio.repository.itf.ProjectRepository;
import com.baro.portfolio.repository.mapper.ProjectMapper;
import com.baro.portfolio.repository.mapper.UserProjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Repository
public class ProjectRepositoryImpl implements ProjectRepository {

    private final ProjectMapper projectMapper;
    private final UserProjectMapper userProjectMapper;

    @Override
    public int save(int userSeq, Project project, String myPart) {

        this.projectMapper.save(project);
        int createdProjectSeq = project.getSeq();

        userProjectMapper.addContributor(userSeq, createdProjectSeq, myPart);

        return project.getSeq();
    }

    @Override
    public Optional<Project> findBySeq(int seq) {

        return this.projectMapper.findBySeq(seq);
    }

    @Override
    public int update(int seq, Project project) {

        return this.projectMapper.update(seq, project);
    }

    @Override
    public boolean remove(int seq) {

        return this.projectMapper.remove(seq) == 1;
    }

    @Override
    public List<Integer> findContributorsBySeq(int seq) {

        return this.userProjectMapper.findUserByProjectSeq(seq);
    }
}
