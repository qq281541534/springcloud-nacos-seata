package com.work.common.component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuyu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespBody<T> {

    private Integer status;

    private String message;

    private T data;

    public RespBody(RespCode respCode) {
        this.status = respCode.getCode();
        this.message = respCode.getMessage();
    }

    public RespBody(T data) {
        this.status = RespCode.SUCCESS.getCode();
        this.message = RespCode.SUCCESS.getMessage();
        this.data = data;
    }


}
