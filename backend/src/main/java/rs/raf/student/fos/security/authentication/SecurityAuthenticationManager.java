package rs.raf.student.fos.security.authentication;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SecurityAuthenticationManager implements AuthenticationManager {

    private final AuthenticationProvider authenticationProvider;
    private       ProviderManager        providerManager;

    @PostConstruct
    public void init() {
        this.providerManager = new ProviderManager(List.of(authenticationProvider));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return providerManager.authenticate(authentication);
    }
}
