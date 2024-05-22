package com.baro.portfolio.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ProjectDateDto {

    @NotNull
    protected Date start;

    @NotNull
    protected Date end;
}
