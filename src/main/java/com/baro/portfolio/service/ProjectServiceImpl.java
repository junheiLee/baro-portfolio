package com.baro.portfolio.service;

import com.baro.portfolio.domain.PortfolioProject;
import com.baro.portfolio.domain.Project;
import com.baro.portfolio.domain.User;
import com.baro.portfolio.repository.itf.ProjectRepository;
import com.baro.portfolio.service.itf.ProjectService;
import com.baro.portfolio.web.dto.CreateProjectDto;
import com.baro.portfolio.web.dto.EditProjectDto;
import com.baro.portfolio.web.dto.result.PortfolioProjectInfo;
import com.baro.portfolio.web.dto.result.ProjectInfo;
import com.baro.portfolio.web.dto.result.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public List<PortfolioProjectInfo> portfolioProjects(int userSeq) {

        List<PortfolioProject> projects = projectRepository.findPortfolioProjects(userSeq);
        return projects.stream().map(PortfolioProjectInfo::fromEntity).toList();
    }

    @Override
    public List<ProjectInfo> projects(Integer userSeq, Boolean isPublic) {

        List<Project> projects = projectRepository.findByUserSeqAndIsPublic(userSeq, toInteger(isPublic));
        return projects.stream().map(ProjectInfo::fromEntity).toList();
    }

    private Integer toInteger(Boolean isPublic) {

        if (isPublic != null) {
            return isPublic ? 1 : 0;
        }
        return null;
    }

    @Override
    @Transactional
    public int save(int userSeq, CreateProjectDto dto) {

        return projectRepository.save(userSeq, dto.editIsProceeding().toProjectEntity(), dto.getMyPart());
    }

    @Override
    public ProjectInfo read(int projectSeq) {

        Project project = projectRepository.findBySeq(projectSeq).orElseThrow(() -> new RuntimeException("임시"));
        ProjectInfo projectInfo = ProjectInfo.fromEntity(project);

        List<User> users = projectRepository.findContributorsBySeq(projectSeq);
        List<UserInfo> contributors = users.stream().map(UserInfo::fromEntity).toList();

        projectInfo.addContributors(contributors);

        return projectInfo;
    }

    @Override
    public EditProjectDto read(int userSeq, int projectSeq) {

        EditProjectDto dto = new EditProjectDto(read(projectSeq));
        String myPart = projectRepository.findMyPart(userSeq, projectSeq);
        dto.setMyPart(myPart);

        return dto;
    }


    @Override
    @Transactional
    public void update(int userSeq, int projectSeq, EditProjectDto dto) {

        Project updateParam = dto.editIsProceeding().toProjectEntity();
        projectRepository.update(projectSeq, updateParam, userSeq, dto.getMyPart());
    }

    @Override
    @Transactional
    public void delete(int userSeq, int projectSeq) {

        projectRepository.removeContributor(userSeq, projectSeq);
        int remainContributor = projectRepository.countContributors(projectSeq);

        if (remainContributor == 0) {
            projectRepository.removeProject(projectSeq);
        }
    }

    @Override
    public List<UserInfo> findContributors(int projectSeq) {

        List<User> users = projectRepository.findContributorsBySeq(projectSeq);

        return users.stream().map(UserInfo::fromEntity).toList();
    }

}
