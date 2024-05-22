package com.baro.portfolio.repository.mapper;

import com.baro.portfolio.domain.PortfolioProject;
import com.baro.portfolio.domain.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProjectMapper {

    List<PortfolioProject> findPortfolioProjects(int userSeq);

    List<Project> findByUserSeqAndIsPublic(Integer userSeq, Integer isPublic);

    void save(Project project);

    Optional<Project> findBySeq(int projectSeq);

    Integer update(@Param("projectSeq") Integer projectSeq,
                   @Param("updateParam") Project project);

    Integer remove(Integer projectSeq);

}
