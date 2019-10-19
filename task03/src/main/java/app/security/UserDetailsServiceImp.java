package app.security;

import app.dao.UserDAO;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDAO.getByLogin(login);
        if (user != null) {
            return user.getUserDetails();
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }
}
