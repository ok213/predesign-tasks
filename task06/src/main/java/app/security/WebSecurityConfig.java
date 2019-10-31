package app.security;

import app.security.api.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final RestAuthenticationEntryPoint authEntryPoint;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, RestAuthenticationEntryPoint authEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.authEntryPoint = authEntryPoint;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/api/v1/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/api/v1/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/api/v1/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PATCH, "/api/v1/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/api/v1/**").hasRole("ADMIN")
                .and()
                    .httpBasic()
                    .authenticationEntryPoint(authEntryPoint)
        ;

        http
                .csrf().disable()
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/login").permitAll()
                    .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                    .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .usernameParameter("login")
                    .passwordParameter("password")
                    .successHandler(new CustomAuthenticationSuccessHandler())
                    .failureHandler(new CustomAuthenticationFailureHandler())
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .permitAll()
        ;

    }

}
