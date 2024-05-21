package com.baro.portfolio.repository;

import com.baro.portfolio.domain.Project;
import com.baro.portfolio.repository.itf.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class ProjectRepositoryTest {

    private int savedProjectSeq;
    @Autowired
    ProjectRepository projectRepository;

    @BeforeEach
    void save() {
        Project project = Project.builder().title("test!!").isPublic(0)
                .start(Date.valueOf("2024-05-10")).end(Date.valueOf("2024-05-16"))
                .headcount(1).description("project repository test").build();

        projectRepository.save(1, project, "myPart");
        savedProjectSeq = project.getSeq();
    }

    @Test
    void findBySeq() {
        //when
        Project findedProject1 = projectRepository.findBySeq(savedProjectSeq).get();
        Project findedProject2 = projectRepository.findBySeq(savedProjectSeq).get();

        //then
        assertThat(findedProject1).isEqualTo(findedProject2);
    }

    @Test
    void update() {
        //given
        Project project = Project.builder().title("update!!").isPublic(1)
                .start(Date.valueOf("2024-04-01")).end(Date.valueOf("2024-05-16"))
                .headcount(2).github("https://github.com/junheiLee")
                .description("project update test").build();

        //when
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception ignored) {
        }

        Integer updatedRowCount = projectRepository.update(savedProjectSeq, project, 1, "mypart");
        Project updatedProject = projectRepository.findBySeq(savedProjectSeq).get();

        //then
        assertThat(updatedProject.getTitle()).isEqualTo("update!!");
        assertThat(updatedProject.getModifiedAt()).isNotEqualTo(updatedProject.getCreatedAt());
        assertThat(updatedRowCount).isEqualTo(1);
    }

    @Test
    void removeProject() {
        //when
        boolean isRemove = projectRepository.removeProject(savedProjectSeq);

        //then
        assertThat(isRemove).isTrue();
    }

}
