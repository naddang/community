package com.dev_cbj.community.comm_code.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommCodeDTO {
    private String code;
    private String codeName;
    private String parentCode;
    private String codeDesc;
    private String useYn;
    private int codeOrder;
}
