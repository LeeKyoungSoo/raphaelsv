package com.bethesda.raphaelsv.security;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.sql.DataSource;

@Log
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;
    com.bethesda.raphaelsv.security.ZerockUserService zerockUserService;


    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }
    */

    @Override
    // js, css, image 설정은 보안 설정의 영향 밖에 있도록 만들어주는 설정.
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected  void configure(HttpSecurity http) throws  Exception {
        log.info("security config........");

        http.csrf()
             .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

        http.authorizeRequests()
                .antMatchers("/basic/**","/etcmng/**", "/incom/**")
                .hasRole("M");

        http.authorizeRequests()
                .and()
                .formLogin()
                .loginPage("/accounts/login")
                .defaultSuccessUrl("/incom/incomjego");

        http.authorizeRequests()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/accounts/accessDenied");

        http.authorizeRequests()
                .and()
                .logout()
                .logoutUrl("/accounts/logout")
                .logoutSuccessUrl("/incom/incomjego")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "SOME", "OTHER", "COOKIES");

        http.rememberMe()
                .key("zerock")
                .userDetailsService(zerockUserService)
                .tokenRepository(getTokenSeries())
                .tokenValiditySeconds(60*60*24);

        //http.cors().and();
        //http.csrf().disable();
    }

    private PersistentTokenRepository getTokenSeries() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    /*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        log.info("build Auth global........");

        auth.inMemoryAuthentication()
                .withUser("manager")
                .password("{noop}1111")
                .roles("MANAGER");
    }
    */
}
