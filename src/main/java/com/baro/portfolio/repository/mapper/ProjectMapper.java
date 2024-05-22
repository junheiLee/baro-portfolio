package com.baro.portfolio.repository.mapper;

import com.baro.portfolio.domain.PortfolioProject;
import com.baro.portfolio.domain.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProjectMapper {

    List<Project> findByUserSeqAndIsPublic(Integer userSeq, Integer isPublic);

    List<PortfolioProject> findPortfolioProjects(int userSeq);

    void save(Project project);

    Optional<Project> findBySeq(Integer seq);

    Integer update(@Param("seq") Integer seq,
                   @Param("updateParam") Project project);

    Integer remove(Integer seq);

}
