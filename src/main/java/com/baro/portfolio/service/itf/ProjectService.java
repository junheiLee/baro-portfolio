package com.baro.portfolio.service.itf;

import com.baro.portfolio.web.dto.CreateProjectDto;
import com.baro.portfolio.web.dto.EditProjectDto;
import com.baro.portfolio.web.dto.result.ProjectInfo;

import java.util.List;

public interface ProjectService {

    int save(int userSeq, CreateProjectDto dto);

    ProjectInfo read(Integer projectSeq);

    List<Integer> findContributors(int projectSeq);

    void update(int userSeq, int projectSeq, EditProjectDto dto);

    String findMyPart(int userSeq, int projectSeq);
}
