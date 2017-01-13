package learn.spring.core.services;

import learn.spring.core.dao.UserDAO;
import learn.spring.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by artem_shevtsov on 1/13/17.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDAO.getUserByEmail(s);
        if(user == null){
            throw new UsernameNotFoundException(String.format("User with email %s was not found", s));
        }
        String rolesString = user.getRoles();
        List<GrantedAuthority> grandedAuthorities = getGrandedAuthorities(rolesString);
        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                grandedAuthorities);
        return userDetails;
    }

    private List<GrantedAuthority> getGrandedAuthorities(String rolesString){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<String> rolesArray = Arrays.asList(rolesString.split(","));
        rolesArray.forEach(s -> {
            String role = "ROLE_" + s.toUpperCase().trim();
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
            grantedAuthorities.add(authority);
        });

        return grantedAuthorities;
    }
}
