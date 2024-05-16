package com.baro.portfolio.repository;

import com.baro.portfolio.domain.Project;
import com.baro.portfolio.repository.itf.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class ProjectRepositoryTest {

    private int beforeSaveSeq;
    @Autowired
    ProjectRepository projectRepository;

    @BeforeEach
    void save() {
        Project project = Project.builder().title("test!!").isPublic(0)
                .start(Date.valueOf("2024-05-10")).end(Date.valueOf("2024-05-16"))
                .headcount(1).description("project repository test").build();

        projectRepository.save(project);
        beforeSaveSeq = project.getSeq();

    }

    @Test
    void findBySeq() {

        //when
        Project findedProject1 = projectRepository.findBySeq(beforeSaveSeq).get();
        Project findedProject2 = projectRepository.findBySeq(beforeSaveSeq).get();

        //then
        assertThat(findedProject1).isEqualTo(findedProject2);
        assertThat(findedProject1).isSameAs(findedProject2);
    }

    @Test
    void update() {

    }

}
