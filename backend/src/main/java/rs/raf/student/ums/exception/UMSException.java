package rs.raf.student.ums.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import rs.raf.student.ums.logger.Logger;

@Getter
public class UMSException extends RuntimeException {

    private final HttpStatusCode httpStatus;

    public UMSException(IException exception, String... args) {
        Logger.log(exception.severity(), exception.pattern(), args);

        httpStatus = exception.httpStatus();
    }

}
