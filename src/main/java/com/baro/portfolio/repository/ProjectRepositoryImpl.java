package com.baro.portfolio.repository;

import com.baro.portfolio.domain.PortfolioProject;
import com.baro.portfolio.domain.Project;
import com.baro.portfolio.domain.User;
import com.baro.portfolio.repository.itf.ProjectRepository;
import com.baro.portfolio.repository.mapper.ProjectMapper;
import com.baro.portfolio.repository.mapper.UserProjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepository {

    private final ProjectMapper projectMapper;
    private final UserProjectMapper userProjectMapper;

    @Override
    public List<PortfolioProject> findPortfolioProjects(int userSeq) {

        return projectMapper.findPortfolioProjects(userSeq);
    }

    @Override
    public List<Project> findByUserSeqAndIsPublic(Integer userSeq, Integer isPublic) {

        return projectMapper.findByUserSeqAndIsPublic(userSeq, isPublic);
    }

    @Override
    public int save(int userSeq, Project project, String myPart) {

        projectMapper.save(project);
        int createdProjectSeq = project.getSeq();

        userProjectMapper.addContributor(userSeq, createdProjectSeq, myPart);

        return project.getSeq();
    }

    @Override
    public Optional<Project> findBySeq(int projectSeq) {

        return projectMapper.findBySeq(projectSeq);
    }

    @Override
    public String findMyPart(int userSeq, int projectSeq) {

        return userProjectMapper.findMyPart(userSeq, projectSeq);
    }

    @Override
    public List<User> findContributorsBySeq(int projectSeq) {

        return userProjectMapper.findUserByProjectSeq(projectSeq);
    }

    @Override
    public void update(int projectSeq, Project project, int userSeq, String myPart) {

        userProjectMapper.updateMyPart(userSeq, projectSeq, myPart);
        projectMapper.update(projectSeq, project);
    }

    @Override
    public void removeContributor(int userSeq, int projectSeq) {

        userProjectMapper.remove(userSeq, projectSeq);
    }

    @Override
    public int countContributors(int projectSeq) {

        return userProjectMapper.findUserByProjectSeq(projectSeq).size();
    }

    @Override
    public void removeProject(int projectSeq) {

        projectMapper.remove(projectSeq);
    }
}
