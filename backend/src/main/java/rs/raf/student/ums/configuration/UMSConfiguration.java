package rs.raf.student.ums.configuration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UMSConfiguration {

    public static final String SEPARATOR = ".";
    public static final String BEEN_NAME = "configurations";

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
