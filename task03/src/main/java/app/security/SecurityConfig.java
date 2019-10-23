package app.security;

import app.handler.CustomAccessDeniedHandler;
import app.handler.CustomAuthenticationFailureHandler;
import app.handler.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/user**").access("hasRole('ROLE_USER')")
                .antMatchers("/admin**").access("hasRole('ROLE_ADMIN')")
                .and()
                    // указываем страницу с формой логина
                    .formLogin()
                    // указываем action с формы логина
                    .loginPage("/login")
                    // Указываем параметры логина и пароля с формы логина
                    .usernameParameter("login")
                    .passwordParameter("password")
                    .loginProcessingUrl("/login")
                    .successHandler(new CustomAuthenticationSuccessHandler())
                    .failureHandler(new CustomAuthenticationFailureHandler())
                .and()
                    .logout()
                    .logoutSuccessUrl("/login")
                    // делаем не валидной текущую сессию
//                    .invalidateHttpSession(true)
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and()
                .csrf().disable()
        ;



//        http.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
//            .and()
//            .httpBasic() // Authenticate users with HTTP basic authentication
//            .and()
//            .logout().permitAll().logoutUrl("/logout").invalidateHttpSession(true)
//        ;

    }

}
