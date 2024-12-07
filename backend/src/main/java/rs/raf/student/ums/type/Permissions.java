package rs.raf.student.ums.type;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Objects;

public class Permissions {

    public static final Permissions NONE       = Permissions.of(0);
    public static final Permissions ALL        = Permissions.of(0xFFFFFFFF); //NOTE: don't be lazy ;)
    public static final Permissions CAN_READ   = Permissions.of(1 << 0);
    public static final Permissions CAN_CREATE = Permissions.of(1 << 1);
    public static final Permissions CAN_UPDATE = Permissions.of(1 << 2);
    public static final Permissions CAN_DELETE = Permissions.of(1 << 3);

    private Integer flags;

    //region Constructors

    public Permissions() {
        this(NONE);
    }

    public Permissions(Integer flags) {
        this.flags = flags;
    }

    public Permissions(Permissions... permissions) {
        this(0);

        add(permissions);
    }

    public static Permissions of() {
        return of(NONE);
    }

    public static Permissions of(Integer flags) {
        return new Permissions(flags);
    }

    public static Permissions of(Permissions permissions) {
        return new Permissions(permissions);
    }

    //endregion Constructors

    //region Data

    public Integer flags() {
        return flags;
    }

    public Permissions add(Permissions... permissions) {
        Arrays.stream(permissions)
              .forEach(permission -> flags |= permission.flags());

        return this;
    }

    public Permissions remove(Permissions... permissions) {
        Arrays.stream(permissions)
              .forEach(permission -> flags &= ~permission.flags());

        return this;
    }

    public Permissions toggle(Permissions... permissions) {
        Arrays.stream(permissions)
              .forEach(permission -> flags ^= permission.flags());

        return this;
    }

    public Permissions clear() {
        flags = 0;

        return this;
    }

    //endregion Data

    //region Object

    public boolean equals(final Object object) {
        if (object == this)
            return true;

        if (object instanceof Permissions permissions)
            return Objects.equals(permissions.flags, flags);

        return false;
    }

    public int hashCode() {
        return Objects.hash(flags);
    }

    public String toString() {
        return MessageFormat.format("""
                                    {0}: '{' flags = {1} '}'\
                                    """,
                                    Permissions.class.getSimpleName(), flags);
    }

    //endregion Object

}
