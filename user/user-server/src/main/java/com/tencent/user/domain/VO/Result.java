package com.tencent.user.domain.VO;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Result<T> implements Serializable {

    private Integer code;

    private String msg;

    private T data;

    private LocalDateTime time;

    public static Result success() {
        Result<Object> result = new Result<>();
        result.code = 200;
        result.msg = "success";
        result.time = LocalDateTime.now();
        return result;
    }

    public static Result success(Object data) {
        Result<Object> result = new Result<>();
        result.code = 200;
        result.data = data;
        result.msg = "success";
        result.time = LocalDateTime.now();
        return result;
    }

    public static Result success(String msg, Object data) {
        Result<Object> result = new Result<>();
        result.code = 200;
        result.data = data;
        result.msg = msg;
        result.time = LocalDateTime.now();
        return result;
    }

    public Result error(String msg) {

        Result<Object> result = new Result<>();

        result.code = 500;
        result.msg = msg;
        result.time = LocalDateTime.now();
        return result;
    }

    public Result error(Integer code, String msg) {
        Result<Object> result = new Result<>();
        result.code = code;
        result.msg = msg;
        result.time = LocalDateTime.now();
        return result;
    }
}
