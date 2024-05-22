package com.baro.portfolio.service.itf;

import com.baro.portfolio.web.dto.CreateProjectDto;
import com.baro.portfolio.web.dto.EditProjectDto;
import com.baro.portfolio.web.dto.result.PortfolioProjectInfo;
import com.baro.portfolio.web.dto.result.ProjectInfo;
import com.baro.portfolio.web.dto.result.UserInfo;

import java.util.List;

public interface ProjectService {

    /**
     * 포트폴리오에 기재하는 project list 반환 기능
     *
     * @param userSeq   해당 포트폴리오의 User 식별자
     * @return          개인 기여 포함 project list
     */
    List<PortfolioProjectInfo> portfolioProjects(int userSeq);

    /**
     * 프로젝트 리스트 반환 기능
     * 1. user 식별자가 null이면 public project list 반환
     * 2. isPublic이 null이면, 해당 유저의 project list 반환
     *
     * @param userSeq   user 식별자
     * @param isPublic  프로젝트 공개 여부
     * @return  project list
     */
    List<ProjectInfo> projects(Integer userSeq, Boolean isPublic);

    /**
     * 새 프로젝트 정보 저장 기능
     *
     * @param userSeq   저장하는 user의 식별자
     * @param dto       저장하는 프로젝트 정보
     * @return          생성된 project의 식별자 반환
     */
    int save(int userSeq, CreateProjectDto dto);

    /**
     * 기여자 포함 project   정보 단건 반환
     *
     * @param projectSeq    대상 프로젝트 식별자
     * @return              기여자 포함 project 정보
     */
    ProjectInfo read(int projectSeq);

    /**
     * 기여자 포함, 개인 기여 포함 project 정보 단건 반환
     *
     * @param projectSeq    대상 프로젝트 식별자
     * @param userSeq       개인 기여를 조회하고자 하는 user 식별자
     * @return              기여자 포함, 개인 기여 포함 project 정보
     */
    EditProjectDto read(int projectSeq, int userSeq);

    /**
     * 개인 기여 포함 프로젝트 정보 수정
     *
     * @param userSeq       수정하는 user 식별자
     * @param projectSeq    수정하고자 하는 project 식별자
     * @param dto           개인 기여 포함 수정 정보
     */
    void update(int userSeq, int projectSeq, EditProjectDto dto);

    /**
     * 프로젝트 삭제 기능
     * 기여자에서 제거해 해당 user의 프로젝트 목록에서 제외됨
     * 기여자가 0이면 프로젝트 삭제
     * 
     * @param userSeq       제거할 user 식별자
     * @param projectSeq    삭제하고자 하는 project 식별자
     */
    void delete(int userSeq, int projectSeq);

    /**
     * 프로젝트 기여자 목록 반환 기능
     *
     * @param projectSeq    대상 프로젝트 식별자
     * @return              프로젝트에 기여한 user 목록
     */
    List<UserInfo> findContributors(int projectSeq);

}
