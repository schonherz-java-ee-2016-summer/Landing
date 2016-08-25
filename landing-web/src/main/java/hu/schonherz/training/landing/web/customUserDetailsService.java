package hu.schonherz.training.landing.web;

import hu.schonherz.training.landing.service.client.api.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.ejb.EJB;

@Service("customUserDetailsService")
@EJB(name = "UserService", beanInterface = UserService.class)
public class customUserDetailsService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
