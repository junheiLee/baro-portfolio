package com.baro.portfolio.service;

import com.baro.portfolio.domain.Project;
import com.baro.portfolio.repository.itf.ProjectRepository;
import com.baro.portfolio.service.itf.ProjectService;
import com.baro.portfolio.web.dto.CreateProjectDto;
import com.baro.portfolio.web.dto.EditProjectDto;
import com.baro.portfolio.web.dto.result.ProjectInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

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

    @Transactional
    @Override
    public int save(int userSeq, CreateProjectDto dto) {

        return projectRepository.save(userSeq, dto.editIsProceeding().toProjectEntity(), dto.getMyPart());
    }

    @Override
    public ProjectInfo read(Integer projectSeq) {

        Project project = projectRepository.findBySeq(projectSeq).orElseThrow(() -> new RuntimeException("임시"));

        ProjectInfo projectInfo = ProjectInfo.fromEntity(project);
        List<Integer> contributors = projectRepository.findContributorsBySeq(projectSeq);

        projectInfo.addContributors(contributors);

        return projectInfo;
    }

    @Override
    public List<Integer> findContributors(int projectSeq) {
        return projectRepository.findContributorsBySeq(projectSeq);
    }

    @Override
    public void update(int userSeq, int projectSeq, EditProjectDto dto) {
        projectRepository.update(projectSeq, dto.editIsProceeding().toProjectEntity(), userSeq, dto.getMyPart());
    }

    @Override
    public String findMyPart(int userSeq, int projectSeq) {
        return projectRepository.findMyPart(userSeq, projectSeq);
    }

    @Override
    public void delete(int userSeq, int projectSeq) {
        projectRepository.removeContributor(userSeq, projectSeq);

        int remainContributor = projectRepository.countContributors(projectSeq);

        if (remainContributor == 0) {
            projectRepository.removeProject(projectSeq);
        }
    }


}
