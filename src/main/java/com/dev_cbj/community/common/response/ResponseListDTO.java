package com.dev_cbj.community.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseListDTO<T> extends ResponseDTO {
    private int totalCount;
    private List<T> list;
}
