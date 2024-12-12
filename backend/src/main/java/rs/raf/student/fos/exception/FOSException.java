package rs.raf.student.fos.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import rs.raf.student.fos.logger.Logger;

@Getter
public class FOSException extends RuntimeException {

    private final HttpStatusCode httpStatus;

    public FOSException(IException exception, String... args) {
        Logger.log(exception.severity(), exception.pattern(), args);

        httpStatus = exception.httpStatus();
    }

}
