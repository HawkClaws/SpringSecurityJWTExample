package com.example.demo.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTProvider {

    // Signatureのエンコードに使うシークレットキー
    private static final String TOKEN_SECRET_KEY = "This is secrect!";

    // トークンの有効期間(1時間)
    private static final long TOKEN_VAILD_DURATION = 1000L * 60L * 60L;

    // ユーザ情報を取得するためのサービスクラス
    private final UserDetailsService service;

    @Autowired
    public JWTProvider(UserDetailsService service) {
        this.service = service;
    }

    // UserオブジェクトからJWTを作成する
    public String createToken(User user) {
        // Claimとしてユーザ名とロールを載せる
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("roles", user.getRoles());
        // トークンの開始時間と満了時間を決める
        Date iat = new Date();
        Date exp = new Date(iat.getTime() + TOKEN_VAILD_DURATION);
        // JWTの作成
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(iat)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET_KEY)
                .compact();
    }

    // トークンからユーザ情報を取得する
    public Authentication getAuthentication(final String token) {
        final UserDetails userDetails = this.service.loadUserByUsername(this.getSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // リクエストのHeaderからトークンを取得する
    public String resolveToken(final HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    // トークンの有効期間を検証する
    public boolean validateToken(final String token) {
        try {
            final Jws<Claims> claims = Jwts.parser().setSigningKey(TOKEN_SECRET_KEY).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    // トークンからユーザ名を取得する
    public String getSubject(final String token) {
        return Jwts.parser().setSigningKey(TOKEN_SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }
    
}