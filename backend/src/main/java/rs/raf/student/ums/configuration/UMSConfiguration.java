package rs.raf.student.ums.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import rs.raf.student.ums.type.Permissions;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UMSConfiguration {

    public static final String SEPARATOR = ".";
    public static final String BEEN_NAME = "configurations";

    //region General

    @NoArgsConstructor
    @Accessors(fluent = true)
    @Configuration(Application.BEEN_NAME)
    public static class Application {

        public static final String BEEN_NAME = UMSConfiguration.BEEN_NAME + SEPARATOR + "application";

        @Getter
        private static String name;
        @Getter
        private static String contextPath;
        @Getter
        private static Integer port;

        @Value("${spring.application.name}")
        private void setName(String value) {
            name = value;
        }

        @Value("${server.port}")
        private void setPort(Integer value) {
            port = value;
        }

        @Value("${server.servlet.context-path}")
        private void setContextPath(String value) {
            contextPath = value;
        }

    }

    @NoArgsConstructor
    @Accessors(fluent = true)
    @Configuration(Jwt.BEEN_NAME)
    public static class Jwt {

        public static final String BEEN_NAME = UMSConfiguration.BEEN_NAME + SEPARATOR + "jwt";

        @Getter
        private static String secret;
        @Getter
        private static Integer expirationInHours;

        @Value("${ums.jwt.secret}")
        private void setSecret(String value) {
            secret = value;
        }

        @Value("${ums.jwt.expiration_time_hours}")
        private void setExpiration(Integer value) {
            expirationInHours = value;
        }

    }

    @NoArgsConstructor
    @Accessors(fluent = true)
    @Configuration(Cors.BEEN_NAME)
    public static class Cors {

        public static final String BEEN_NAME = UMSConfiguration.BEEN_NAME + SEPARATOR + "cors";

        @Getter
        private static String[] allowedOrigins;
        @Getter
        private static String[] allowedMethods;
        @Getter
        private static String[] allowedHeaders;
        @Getter
        private static Boolean allowCredentials;
        @Getter
        private static String mappingPath;

        @Value("${ums.cors.allowed-origins}")
        public void setAllowedOrigins(String value) {
            allowedOrigins = value.split(",");
        }

        @Value("${ums.cors.allowed-methods}")
        public void setAllowedMethods(String value) {
            allowedMethods = value.split(",");
        }

        @Value("${ums.cors.allowed-headers}")
        public void setAllowedHeaders(String value) {
            allowedHeaders = value.split(",");
        }

        @Value("${ums.cors.allow-credentials}")
        public void setAllowCredentials(Boolean value) {
            allowCredentials = value;
        }

        @Value("${ums.cors.mapping-path}")
        public void setMappingPath(String value) {
            mappingPath = value;
        }

    }

    //endregion General

    //region Controller

    @NoArgsConstructor
    @Configuration(Controller.BEEN_NAME)
    public static class Controller {

        public static final String BEEN_NAME = UMSConfiguration.BEEN_NAME + SEPARATOR + "controller";

        @NoArgsConstructor
        @Configuration(Endpoint.BEEN_NAME)
        public static class Endpoint {

            public static final String BEEN_NAME = Controller.BEEN_NAME + SEPARATOR + "endpoint";

            @NoArgsConstructor
            @Configuration(User.BEEN_NAME)
            public static class User {

                public static final String BEEN_NAME = Endpoint.BEEN_NAME + SEPARATOR + "user";

                private static final String BASE    = "/users";
                public static final String  GET_ALL = BASE;
                public static final String  GET_ONE = BASE + "/{id}";
                public static final String  LOGIN   = BASE + "/login";
                public static final String  CREATE  = BASE;
                public static final String  UPDATE  = BASE + "/{id}";
                public static final String  PATCH   = BASE + "/{id}";
                public static final String  DELETE  = BASE + "/{id}";

            }

        }

        @NoArgsConstructor
        @Configuration(Permission.BEEN_NAME)
        public static class Permission {

            public static final String BEEN_NAME = Controller.BEEN_NAME + SEPARATOR + "permissions";

            @NoArgsConstructor
            @Configuration(User.BEEN_NAME)
            public static class User {

                public static final String BEEN_NAME = Permission.BEEN_NAME + SEPARATOR + "user";

                public static final Permissions GET_ALL = Permissions.of(Permissions.CAN_READ);
                public static final Permissions GET_ONE = Permissions.of(Permissions.CAN_READ);
                public static final Permissions LOGIN   = Permissions.of(Permissions.NONE);
                public static final Permissions CREATE  = Permissions.of(Permissions.CAN_READ, Permissions.CAN_CREATE);
                public static final Permissions UPDATE  = Permissions.of(Permissions.CAN_READ, Permissions.CAN_UPDATE);
                public static final Permissions PATCH   = Permissions.of(Permissions.CAN_READ, Permissions.CAN_UPDATE);
                public static final Permissions DELETE  = Permissions.of(Permissions.CAN_READ, Permissions.CAN_DELETE);

            }

        }

    }

    //endregion Controller

    //region Permission

    @NoArgsConstructor
    @Configuration(Permission.BEEN_NAME)
    public static class Permission {

        public static final String BEEN_NAME = UMSConfiguration.BEEN_NAME + SEPARATOR + "permissions";

        public static final String NONE       = "none";
        public static final String CAN_READ   = "can_read";
        public static final String CAN_CREATE = "can_create";
        public static final String CAN_UPDATE = "can_update";
        public static final String CAN_DELETE = "can_delete";

    }

    //endregion Permission

}
