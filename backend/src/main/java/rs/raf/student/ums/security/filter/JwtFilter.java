package rs.raf.student.ums.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import rs.raf.student.ums.exception.UMSException;
import rs.raf.student.ums.repository.IUserRepository;
import rs.raf.student.ums.utilities.JwtUtilities;

import java.io.IOException;

import static rs.raf.student.ums.exception.ExceptionType.FIND_USER_NOT_FOUND_EMAIL;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final IUserRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        try {
            String token = request.getHeader(HttpHeaders.AUTHORIZATION);

            if (token != null && JwtUtilities.isValid(token)) {
                String      email       = JwtUtilities.extractEmail(token);
                UserDetails userDetails = repository.findByEmail(email)
                                                    .orElseThrow(() -> new UMSException(FIND_USER_NOT_FOUND_EMAIL, email));

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                                                                                                             userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        catch (Exception ignore) { }

        filterChain.doFilter(request, response);
    }

}
