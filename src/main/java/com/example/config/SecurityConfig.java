// SecurityConfig.java

package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        // spring securityで無視するリクエストパスを設定
        web.ignoring().antMatchers("/css/**", "/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().ignoringAntMatchers("/sample");
    	
    	http.authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/api/login").permitAll()
        .antMatchers("/public/**").permitAll()
        .antMatchers("/api/**").authenticated();
		// 独自フィルターの利用
		// デフォルトのAuthenticationManagerを利用する
		http.addFilter(new JsonAuthenticationFilter(authenticationManager()));
		// csrfを無効にしておく
		// またCookieを利用してcsrf対策を行う
		http.csrf().ignoringAntMatchers("/api/**");
    }
}
