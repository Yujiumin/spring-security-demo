package org.example.bean;

import org.example.config.properties.SpringSecurityConfigurerConfigurationProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Value("#{springSecurityConfigurerConfigurationProperties.httpSecurity}")
    private SpringSecurityConfigurerConfigurationProperties.HttpSecurity httpSecurity;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启请求授权保护
        http.authorizeRequests()
                // 以下请求不需要授权保护
                .antMatchers(httpSecurity.getPermit())
                .permitAll()
                // 对所有的请求开启授权保护
                .anyRequest()
                // 已认证的请求会被自动授权
                .authenticated();

        // 使用表单授权方式
        http.formLogin()
                // 认证成功处理
                .successHandler(new SpringAuthenticationSuccessHandler())
                // 认证失败处理
                .failureHandler(new SpringAuthenticationFailHandler());

        // 注销登录处理
        http.logout().addLogoutHandler(new SpringLogoutHandler());

        // 异常处理
        http.exceptionHandling()
                // 认证异常处理
                .authenticationEntryPoint(new SpringAuthenticationEntryPoint())
                // 授权异常处理
                .accessDeniedHandler(new SpringAccessDeniedHandler());

        http.sessionManagement()
                .maximumSessions(1);
        http.httpBasic();
        // 关闭CSRF攻击保护
        http.csrf().disable();
        // 开启CORS跨域访问
        http.cors(Customizer.withDefaults());
    }
}
