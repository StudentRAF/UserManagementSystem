package rs.raf.student.ums.utilities;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import rs.raf.student.ums.configuration.UMSConfiguration;
import rs.raf.student.ums.entity.User;
import rs.raf.student.ums.type.AuthenticationSchemeType;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Function;

public class JwtUtilities {

    private static final SecretKey                     key;
    private static final Function<User, String>        generateToken;
    private static final Function<String, Jws<Claims>> parseToken;

    static {
        key = Keys.hmacShaKeyFor(UMSConfiguration.Jwt.secret().getBytes(StandardCharsets.UTF_8));

        generateToken = (user) -> Jwts.builder()
                                      .issuer(UMSConfiguration.Application.name())
                                      .subject(user.email())
                                      .claim("id", user.id())
                                      .claim("email", user.email())
                                      .issuedAt(Date.from(LocalDateTime.now()
                                                                       .atZone(ZoneId.systemDefault())
                                                                       .toInstant()))
                                      .expiration(Date.from(LocalDateTime.now()
                                                                         .plusHours(UMSConfiguration.Jwt.expirationInHours())
                                                                         .atZone(ZoneId.systemDefault())
                                                                         .toInstant()))
                                      .signWith(key)
                                      .compact();

        parseToken = (token) -> Jwts.parser()
                                    .verifyWith(key)
                                    .build()
                                    .parse(token)
                                    .accept(Jws.CLAIMS);
    }

    public static String generateToken(User user) {
        return generateToken.apply(user);
    }

    public static String extractEmail(String token) {
        return parseToken.apply(removeAuthenticationScheme(token))
                         .getPayload()
                         .getSubject();

    }

    public static boolean isValid(String token) {
        try {
            return parseToken.apply(removeAuthenticationScheme(token)) != null;
        }
        catch (Exception ignored) { }

        return false;
    }

    private static String removeAuthenticationScheme(String token) {
        if (token.startsWith(AuthenticationSchemeType.BEARER.getName()))
            return token.substring(AuthenticationSchemeType.BEARER.getName().length() + 1);

        return token;
    }

}
