package com.baro.portfolio.repository.mapper;

import com.baro.portfolio.domain.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface ProjectMapper {

    void save(Project project);

    Optional<Project> findBySeq(Integer seq);

    Integer update(@Param("seq") Integer seq,
                   @Param("updateParam") Project project);

    Integer remove(Integer seq);

}
