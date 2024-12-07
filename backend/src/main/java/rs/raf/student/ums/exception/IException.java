package rs.raf.student.ums.exception;

import org.springframework.http.HttpStatusCode;
import rs.raf.student.ums.logger.Severity;

public interface IException {

    String pattern();

    HttpStatusCode httpStatus();

    Severity severity();

}
