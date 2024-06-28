package com.dev_cbj.community.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private int resultCode;
    private String resultMsg;

    public ResponseDTO(HttpStatus status) {
        this.resultCode = status.value();
        this.resultMsg = status.getReasonPhrase();
    }
}
