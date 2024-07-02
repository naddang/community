package com.dev_cbj.community.common.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider {
    private final Key key;
    private final long tokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;
    private final String issuer;


    //설정에서 선언한 jwt.secret 값을 주입받아 변환한 뒤 key 변수에 할당
    public TokenProvider(@Value("${jwt.secret}") String secret, @Value("${jwt.token-validity-in-seconds}") long tokenValiditySec,
                         @Value("${jwt.token-validity-refresh}") long refreshTokenValiditySec, @Value("${jwt.issuer}")String issuer) {
        this.key = Keys.hmacShaKeyFor(DatatypeConverter.parseBase64Binary(secret));
        this.tokenValidityInMilliseconds = tokenValiditySec * 1000;
        this.refreshTokenValidityInMilliseconds = refreshTokenValiditySec * 1000;
        this.issuer = issuer;
    }

    //토큰 생성
    public JwtDto createToken(Authentication authentication) {
        Date now = new Date();
        SecurityUser principal = (SecurityUser) authentication.getPrincipal();
        //토큰 생성
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuer(issuer)
                .setIssuedAt(now)
                .claim("power", principal.getPower())
                .claim("idx", principal.getIdx())
                .setExpiration(new Date(now.getTime() + tokenValidityInMilliseconds))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        //리프레시 토큰 생성
        String refreshToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("idx", principal.getIdx())
                .setExpiration(new Date(now.getTime() + refreshTokenValidityInMilliseconds))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return JwtDto.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expire(String.valueOf(new Date(now.getTime() + tokenValidityInMilliseconds)))
                .build();
    }


    //토큰에서 인증 정보 조회
    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);
        Object userAuthorities = claims.get("power");

        //권한정보가 없다면 에러
        if (userAuthorities == null) throw new RuntimeException("power");

        //이게 뭘 위한 코드?
        Collection<? extends GrantedAuthority> authorities = Arrays.stream(userAuthorities.toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        //인증 정보를 가지고 Authentication 객체를 생성
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    //액세스 토큰 유효성 검사
    public boolean tokenValidation(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean tokenExpired(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return false;
        } catch (ExpiredJwtException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int getMemberIdx(String token) {
        try {
            Claims claims = parseClaims(token);
            return Integer.parseInt(claims.getSubject());
        } catch (Exception e) {
            return -1;
        }
    }

    //요청객체로부터 토큰 추출
    public String getAccessToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

    //AccessToken -> Claims 변환 메서드
    private Claims parseClaims(String accessToken) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
                .getBody(); // body 에 있는 Claims 반환
    }
}
