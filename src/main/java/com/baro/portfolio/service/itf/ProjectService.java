package com.baro.portfolio.service.itf;

import com.baro.portfolio.domain.Project;
import com.baro.portfolio.web.dto.ProjectRequestDto;

public interface ProjectService {

    int save(ProjectRequestDto dto);

    Project read(Integer projectSeq);
}
