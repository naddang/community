package com.dev_cbj.community.common.auth;

import lombok.Builder;

@Builder
public class JwtDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private String privilege;
    private String expire;
}
