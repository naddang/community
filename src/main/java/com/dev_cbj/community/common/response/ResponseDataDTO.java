package com.dev_cbj.community.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDataDTO<T> extends ResponseDTO {
    private T data;

    public ResponseDataDTO(HttpStatus status) {
        super(status);
    }
}
