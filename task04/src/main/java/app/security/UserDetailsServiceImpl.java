package app.security;

import app.dao.UserDAO;
import app.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

    private final UserDAO userDAO;

    public UserDetailsServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDAO.getByLogin(login);
        if (user != null) {
            return  new org.springframework.security.core.userdetails.User(
                    user.getLogin(), user.getPassword(), user.getAuthorities());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }

}

