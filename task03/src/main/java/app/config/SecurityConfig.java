package app.config;

import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;

    @Autowired
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/**").hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin().loginPage("/login")
                .usernameParameter("login")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/login").permitAll()
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
