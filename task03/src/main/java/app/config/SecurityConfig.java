package app.config;

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/**").authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .usernameParameter("login")
                    .passwordParameter("password")
                    .loginProcessingUrl("/login")
                .and()
                    .logout()
                    .logoutSuccessUrl("/login")
                .and()
                .csrf().disable()
        ;



/*
//        http.formLogin()
//                // указываем страницу с формой логина
//                .loginPage("/login")
//                // указываем action с формы логина
////                .loginProcessingUrl("/loginAction")
//                // указываем URL при неудачном логине
////                .failureUrl("/login?error")
//                // Указываем параметры логина и пароля с формы логина
////                .usernameParameter("j_username")
////                .passwordParameter("j_password")
//                // даем доступ к форме логина всем
//                .permitAll();

//        http.logout()
//                // разрешаем делать логаут всем
//                .permitAll()
//                // указываем URL логаута
//                .logoutUrl("/logout")
//                // указываем URL при удачном логауте
//                .logoutSuccessUrl("/login?logout")
//                // делаем не валидной текущую сессию
//                .invalidateHttpSession(true);


        //        http.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
//                .and()
//                .httpBasic() // Authenticate users with HTTP basic authentication
////                .and()
////                .logout().permitAll().logoutUrl("/logout").invalidateHttpSession(true)
//                ;

 */

    }

}
