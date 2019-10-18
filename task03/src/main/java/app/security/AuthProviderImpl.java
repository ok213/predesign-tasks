package app.security;

import app.dao.UserDAO;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    @Autowired
    private UserDAO userDAO;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        User user = userDAO.getByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        String password = authentication.getCredentials().toString();
        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new UsernamePasswordAuthenticationToken(user, null, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }


//    @Transactional(readOnly = true)
//    @Override
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        User user = userDAO.getByLogin(login);
//        UserBuilder builder = null;
//        if (user != null) {
//            builder = org.springframework.security.core.userdetails.User.withUsername(login);
//            builder.disabled(false);
//            builder.password(user.getPassword());
//            String[] authorities = user.getAuthorities()
//                    .stream().map(a -> a.getAuthority()).toArray(String[]::new);
//
//            builder.authorities(authorities);
//        } else {
//            throw new UsernameNotFoundException("User not found.");
//        }
//        return builder.build();
//    }
}
