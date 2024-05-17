package com.baro.portfolio.service;

import com.baro.portfolio.domain.Project;
import com.baro.portfolio.repository.itf.ProjectRepository;
import com.baro.portfolio.service.itf.ProjectService;
import com.baro.portfolio.web.dto.ProjectRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public int save(ProjectRequestDto dto) {

        return projectRepository.save(dto.setIsProceeding().toEntity());
    }

    @Override
    public Project read(Integer seq) {

        return projectRepository.findBySeq(seq).orElseThrow();
    }
}
