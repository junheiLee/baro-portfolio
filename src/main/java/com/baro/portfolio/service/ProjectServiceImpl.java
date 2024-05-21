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

    @Transactional
    @Override
    public int save(int userSeq, CreateProjectDto dto) {

        return projectRepository.save(userSeq, dto.editIsProceeding().toProjectEntity(), dto.getMyPart());
    }

    @Override
    public ProjectInfo read(Integer projectSeq) {

        ProjectInfo projectInfo = new ProjectInfo();
        Project project = projectRepository.findBySeq(projectSeq).orElseThrow(() -> new RuntimeException("임시"));

        projectInfo.fromEntity(project);
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


}
