package com.baro.portfolio.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter @Setter
public class ProjectDateDto {

    @NotNull(message = "시작 날짜는 필수 값입니다.")
    protected Date start;

    @NotNull(message = "종료 날짜는 필수 값입니다.")
    protected Date end;
}
