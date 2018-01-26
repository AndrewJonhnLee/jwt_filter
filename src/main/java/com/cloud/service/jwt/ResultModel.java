package com.cloud.service.jwt;

import lombok.Data;

@Data
public class ResultModel {

    private Integer status;
    private String message;
    private Object result;
}
