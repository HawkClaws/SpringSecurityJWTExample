package com.example.demo.security;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    // Headerからコンテントタイプを取得するための定数
    private static final String CONTENT_TYPE = "Content-Type";
    
    // JSONデータを保存するためのMap
    private Map<String, String> jsonRequest;

    // ユーザ名を取得する
    @Override
    protected String obtainUsername(HttpServletRequest request) {
    	System.out.println(getUsernameParameter());
    	return getParameter(request, getUsernameParameter());
    }

    // パスワードを取得する
    @Override
    protected String obtainPassword(HttpServletRequest request) {
    	System.out.println(getPasswordParameter());
        return getParameter(request, getPasswordParameter());
    }
    
    // JSONもしくはFormデータのクレデンシャルを取得し、Authenticationとして載せる
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (headerContentTypeIsJson(request)) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                this.jsonRequest = mapper.readValue(request.getReader().lines().collect(Collectors.joining()),
                        new TypeReference<Map<String, String>>() {
                        });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        String username = obtainUsername(request) != null ? obtainUsername(request) : "";
        String password = obtainPassword(request) != null ? obtainPassword(request) : "";
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    // リクエストからパラメータ(ユーザ名とパスワード)を取得する
    private String getParameter(HttpServletRequest request, String parameter) {
        if (headerContentTypeIsJson(request)) {
            return jsonRequest.get(parameter);
        } else {
            return request.getParameter(parameter);
        }
    }

    // HeaderからコンテントタイプがJSONかどうかを判定する
    private boolean headerContentTypeIsJson(HttpServletRequest request) {
        return request.getHeader(CONTENT_TYPE).equals(MediaType.APPLICATION_JSON_VALUE);
    }
}