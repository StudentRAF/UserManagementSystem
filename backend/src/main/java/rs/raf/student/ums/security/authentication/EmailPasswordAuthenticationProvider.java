package rs.raf.student.ums.security.authentication;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Component;
import rs.raf.student.ums.security.password.UMSPasswordEncoder;
import rs.raf.student.ums.service.user.IUserService;

@Component
public class EmailPasswordAuthenticationProvider extends DaoAuthenticationProvider {

    public EmailPasswordAuthenticationProvider(@Lazy IUserService userService, UMSPasswordEncoder passwordEncoder) {
        setUserDetailsService(userService);
        setPasswordEncoder(passwordEncoder);
    }

}
