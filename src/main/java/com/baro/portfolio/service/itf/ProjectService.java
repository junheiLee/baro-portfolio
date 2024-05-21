package com.baro.portfolio.service.itf;

import com.baro.portfolio.web.dto.ProjectRequestDto;
import com.baro.portfolio.web.dto.result.ProjectInfo;

public interface ProjectService {

    int save(int userSeq, ProjectRequestDto dto);

    ProjectInfo read(Integer projectSeq);
}
