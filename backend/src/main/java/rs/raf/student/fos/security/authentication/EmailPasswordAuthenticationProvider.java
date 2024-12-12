package rs.raf.student.fos.security.authentication;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Component;
import rs.raf.student.fos.security.password.FOSPasswordEncoder;
import rs.raf.student.fos.service.user.IUserService;

@Component
public class EmailPasswordAuthenticationProvider extends DaoAuthenticationProvider {

    public EmailPasswordAuthenticationProvider(@Lazy IUserService userService, FOSPasswordEncoder passwordEncoder) {
        setUserDetailsService(userService);
        setPasswordEncoder(passwordEncoder);
    }

}
