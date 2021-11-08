package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// ログインが成功した場合の処理のためのHandler
	private final JWTAuthenticationSuccessHandler successHandler;

	// ログイン以降の認証認可のためのFilter
	private final JWTAuthenticationFilter filter;

	@Autowired
	public SecurityConfig(JWTAuthenticationSuccessHandler successHandler, JWTAuthenticationFilter filter) {
		this.successHandler = successHandler;
		this.filter = filter;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(final HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				// Basic認証を使わない
				.httpBasic().disable()
				// CSRF設定を使わない
				.csrf().disable()
				// セッションはStatelessなので使わない
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				// FormLoginは使わない
				.formLogin().disable()

				.authorizeRequests()
                // ログイン処理ようのURLには認証認可なしでアクセスできる
                .antMatchers("/api/v1/web/login/").permitAll()
                .anyRequest().hasRole("USER").and()
				// デフォルトのFilter設定を変える
				.addFilterBefore(this.filter, UsernamePasswordAuthenticationFilter.class)
				.addFilterAt(getJsonUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

	}
	

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	// カスタムUsernamePasswordAuthenticationFilterの設定
	private JsonUsernamePasswordAuthenticationFilter getJsonUsernamePasswordAuthenticationFilter() {
		JsonUsernamePasswordAuthenticationFilter jsonFilter = new JsonUsernamePasswordAuthenticationFilter();
		try {
			// ログインを処理するURLの設定
			jsonFilter.setFilterProcessesUrl("/api/v1/web/login/");
			// AuthenticationManagerの設定
			jsonFilter.setAuthenticationManager(this.authenticationManagerBean());
			// AuthenticationSuccessHandlerの設定
			jsonFilter.setAuthenticationSuccessHandler(this.successHandler);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return jsonFilter;
	}
}
