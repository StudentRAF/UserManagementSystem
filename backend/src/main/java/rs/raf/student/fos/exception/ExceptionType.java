package rs.raf.student.fos.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import rs.raf.student.fos.logger.Severity;

@AllArgsConstructor
public enum ExceptionType implements IException {

    FIND_USER_NOT_FOUND_ID    ("""
                              Could not find user. User with id "{0}" does not exist.\
                              """, Severity.DEBUG, HttpStatus.NOT_FOUND),
    FIND_USER_NOT_FOUND_EMAIL("""
                              Could not find user. User with email "{0}" does not exist.\
                              """, Severity.DEBUG, HttpStatus.NOT_FOUND),

    UPDATE_USER_NOT_FOUND_ID("""
                             Could not update user. User with id "{0}" does not exist.\
                             """, Severity.DEBUG, HttpStatus.NOT_FOUND),

    DELETE_USER_NOT_FOUND_ID("""
                             Could not delete user. User with id "{0}" does not exist.\
                             """, Severity.DEBUG, HttpStatus.NOT_FOUND),

    GENERATE_AUTHORIZATION_TOKEN_NOT_FOUND_AUTHENTICATED_USER("""
                                                              Could not generate authentication token. There is no authenticated user.\
                                                              """, Severity.DEBUG, HttpStatus.NOT_FOUND),
    ;

    private final String     pattern;
    private final Severity   severity;
    private final HttpStatus httpStatus;

    @Override
    public String pattern() {
        return pattern;
    }

    @Override
    public HttpStatusCode httpStatus() {
        return httpStatus;
    }

    @Override
    public Severity severity() {
        return severity;
    }

}
