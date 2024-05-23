package com.baro.portfolio.constant;

import lombok.Getter;

@Getter
public enum ErrorEnum {

    CAN_NOT_EXIST_DATE_SET("canNotExistDateSet", "시작 날짜는 종료 날짜보다 과거여야 합니다."),
    LOGIN_FAIL("loginFail", "아이디 혹은 비밀 번호가 일치하지 않습니다."),
    STRANGER_EDIT_USER("stranger", "해당 리소스에 접근 권한이 없는 사용자입니다."),
    STRANGER_EDIT_PROJECT("stranger", "본인이 참여한 프로젝트만 수정할 수 있습니다."),
    STRANGER_DELETE_PROJECT("stranger", "본인이 참여한 프로젝트만 삭제할 수 있습니다."),
    STRANGER_READ_PROJECT("stranger", "private 프로젝트입니다."),

    NOT_FOUND_USER("notFound", "존재하지 않는 사용자 입니다."),
    NOT_FOUND_PROJECT("notFound", "존재하지 않는 프로젝트 입니다.");

    private final String code;
    private final String message;

    ErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
