package com.istm10.configuration;

import com.istm10.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/api/v*/registration/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated().and()
//                .formLogin().loginPage("/login").defaultSuccessUrl("/homePage");
//        http.csrf().disable().
//                authorizeRequests()
//                .antMatchers("/api/v*/registration/**").permitAll().and()
//                .formLogin()
//                .permitAll().and()
//                .logout().permitAll();

        http.csrf().disable().
                authorizeRequests()
                .antMatchers("/api/v*/registration/**").permitAll()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()   // 其他地址的访问均需验证权限
                .and()
                .formLogin()
                .loginPage("/login")   //  登录页
                .successForwardUrl("/home") // 登入成功后，跳转至指定页面
                .defaultSuccessUrl("/home")   // 访问指定页面，用户未登入，跳转至登入页面，如果登入成功，跳转至用户访问指定页面，用户访问登入页面，默认的跳转页面
                .failureUrl("/login-err").permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/index");

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }
}
