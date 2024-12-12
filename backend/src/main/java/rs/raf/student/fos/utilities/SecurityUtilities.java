package rs.raf.student.fos.utilities;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

import static rs.raf.student.fos.configuration.FOSConfiguration.Controller.Endpoint;
import static rs.raf.student.fos.configuration.FOSConfiguration.Controller.Permission;

public class SecurityUtilities {

    public static class APIv1 {

        public static void authorizeHttpRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorizeHttp) {
            authorizeHttp.requestMatchers(HttpMethod.POST, Endpoint.User.LOGIN)
                         .permitAll();

            authorizeHttp.requestMatchers(HttpMethod.GET, Endpoint.User.GET_ALL)
                         .hasAnyAuthority(Permission.User.GET_ALL.namesArray());

            authorizeHttp.requestMatchers(HttpMethod.GET, Endpoint.User.GET_ONE)
                         .hasAnyAuthority(Permission.User.GET_ONE.namesArray());

            authorizeHttp.requestMatchers(HttpMethod.POST, Endpoint.User.CREATE)
                         .hasAnyAuthority(Permission.User.CREATE.namesArray());

            authorizeHttp.requestMatchers(HttpMethod.PUT, Endpoint.User.UPDATE)
                         .hasAnyAuthority(Permission.User.UPDATE.namesArray());

            authorizeHttp.requestMatchers(HttpMethod.DELETE, Endpoint.User.DELETE)
                         .hasAnyAuthority(Permission.User.DELETE.namesArray());

            authorizeHttp.anyRequest().denyAll();
        }

    }

}
