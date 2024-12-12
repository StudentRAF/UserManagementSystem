package rs.raf.student.fos.exception;

import org.springframework.http.HttpStatusCode;
import rs.raf.student.fos.logger.Severity;

public interface IException {

    String pattern();

    HttpStatusCode httpStatus();

    Severity severity();

}
