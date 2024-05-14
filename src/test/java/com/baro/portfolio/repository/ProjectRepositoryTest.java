package com.baro.portfolio.repository;

import com.baro.portfolio.domain.Project;
import com.baro.portfolio.repository.itf.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class ProjectRepositoryTest {

    @Autowired
    ProjectRepository projectRepository;

    @Test
    void save_findBySeq() {
        //given
        Project project = Project.builder().title("test!!").isPublic(0)
                .start(Date.valueOf("2024-05-10")).end(Date.valueOf("2024-05-16"))
                .headcount(1).description("project repository test").build();

        //when
        projectRepository.save(project);
        Integer createdSeq = project.getSeq();

        Project findedProject1 = projectRepository.findBySeq(createdSeq).get();
        Project findedProject2 = projectRepository.findBySeq(createdSeq).get();

        //then
        assertThat(findedProject1).isEqualTo(findedProject2);
        assertThat(findedProject1).isSameAs(findedProject2);
    }


}
