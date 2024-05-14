package com.baro.portfolio.service.itf;

import com.baro.portfolio.web.dto.ProjectCreateRequestDto;

public interface ProjectService {

    void save(ProjectCreateRequestDto dto);
}
