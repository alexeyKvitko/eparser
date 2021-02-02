package tech.madest.eparser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.madest.eparser.model.User;
import tech.madest.eparser.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {


    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername( String login) throws UsernameNotFoundException {
        User user = findOne( login );
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority( user.getRole() ));
    }

    private List< SimpleGrantedAuthority > getAuthority( String role) {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }


    @Override
    public User findOne(String login) {
        User user = null;
        if ( login.equals("e_admin") ){
            user = new User();
            user.setFirstName("Админ");
            user.setSecondName("Админович");
            user.setLastName("Админов");
            user.setRole("ROLE_ADMIN");
            user.setUsername("e_admin");
            user.setPassword("$2a$10$ScpzSf0IC0eUOShl.utL7uEqVEpXsm.kSIeLUr5UVxvds7G5tFwOW");
        }
        return user;
    }



//    @PostConstruct
//    public void showPassword(){
//                String admin_ps = bcryptEncoder.encode("e_pswd");
//     System.out.println( "PASSWORD FOR admin_pswd is: "+admin_ps );
//    }


}
