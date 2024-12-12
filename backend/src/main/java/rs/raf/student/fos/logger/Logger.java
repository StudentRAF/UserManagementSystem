package rs.raf.student.fos.logger;

import java.text.MessageFormat;
import java.util.Map;

public class Logger {

    private static final Map<Severity, IExceptionLogger> loggerMap = Map.of(
        Severity.TRACE,       Logger::trace,
        Severity.DEBUG,       Logger::debug,
        Severity.INFORMATION, Logger::information,
        Severity.WARNING,     Logger::warning,
        Severity.ERROR,       Logger::error
    );

    private static org.slf4j.Logger logger = null;

    public static void setLogger(org.slf4j.Logger logger) {
        Logger.logger = logger;
    }

    public static void log(Severity severity, String pattern, String... args) {
        loggerMap.get(severity).log(MessageFormat.format(pattern, args));
    }

    private static void trace(String message) {
        logger.trace(message);
    }

    private static void debug(String message) {
        logger.info(message);
    }

    private static void information(String message) {
        logger.info(message);
    }

    private static void warning(String message) {
        logger.warn(message);
    }

    private static void error(String message) {
        logger.error(message);
    }

    @FunctionalInterface
    private interface IExceptionLogger {

        void log(String message);

    }

}
