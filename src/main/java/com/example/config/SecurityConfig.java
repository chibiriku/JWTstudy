// SecurityConfig.java

package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        // spring securityで無視するリクエストパスを設定
        web.ignoring().antMatchers("/css/**", "/resources/**");
        web.ignoring().antMatchers("/api/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().ignoringAntMatchers("/sample");
    	http.cors().configurationSource(this.corsConfigurationSource());
    	
//    	http.formLogin()
//    	.loginProcessingUrl("/api/login");
    	
    	
    	http.authorizeRequests()
        .antMatchers("/api/login").permitAll()
        .antMatchers("/api/login/**").authenticated();
		// 独自フィルターの利用
		// デフォルトのAuthenticationManagerを利用する
		http.addFilter(new JsonAuthenticationFilter(authenticationManager()));
		// csrfを無効にしておく
		// またCookieを利用してcsrf対策を行う
		http.csrf().ignoringAntMatchers("/api/**");
    }
    
    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        corsConfiguration.addExposedHeader("X-AUTH-TOKEN");
        corsConfiguration.addAllowedOrigin("http://localhost:3000");
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", corsConfiguration);
        return corsSource;
    }

}
