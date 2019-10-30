package app.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Order(2)
@Configuration
@EnableWebSecurity
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public RestSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
//            http.csrf().disable().authorizeRequests().anyRequest().permitAll();

            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/api/**").hasRole("ADMIN")
//                    .antMatchers(HttpMethod.GET, "/api/v1/user/*").hasRole("USER")
//                    .antMatchers(HttpMethod.GET, "/api/v1/**").hasRole("ADMIN")
//                    .antMatchers(HttpMethod.POST, "/api/v1/**").hasRole("ADMIN")
//                    .antMatchers(HttpMethod.PUT, "/api/v1/**").hasRole("ADMIN")
//                    .antMatchers(HttpMethod.PATCH, "/api/v1/**").hasRole("ADMIN")
//                    .antMatchers(HttpMethod.DELETE, "/api/v1/**").hasRole("ADMIN")
                    .and()
                    .httpBasic()
            ;
        }

}
