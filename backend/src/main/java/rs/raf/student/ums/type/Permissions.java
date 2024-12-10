package rs.raf.student.ums.type;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import rs.raf.student.ums.adapter.PermissionsAdapter;
import rs.raf.student.ums.configuration.UMSConfiguration;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@JsonSerialize(using = PermissionsAdapter.Serializer.class)
@JsonDeserialize(using = PermissionsAdapter.Deserializer.class)
public class Permissions {

    public static final Permissions NONE       = Permissions.of(UMSConfiguration.Permission.NONE,       0     );
    public static final Permissions CAN_READ   = Permissions.of(UMSConfiguration.Permission.CAN_READ,   1 << 0);
    public static final Permissions CAN_CREATE = Permissions.of(UMSConfiguration.Permission.CAN_CREATE, 1 << 1);
    public static final Permissions CAN_UPDATE = Permissions.of(UMSConfiguration.Permission.CAN_UPDATE, 1 << 2);
    public static final Permissions CAN_DELETE = Permissions.of(UMSConfiguration.Permission.CAN_DELETE, 1 << 3);

    private static final Map<String, Permissions> permissionsNameMap = Map.of(
        CAN_READ.name(),   CAN_READ,
        CAN_CREATE.name(), CAN_CREATE,
        CAN_UPDATE.name(), CAN_UPDATE,
        CAN_DELETE.name(), CAN_DELETE
    );

    private static final Map<Integer, Permissions> permissionsFlagMap = Map.of(
        CAN_READ.flags(),   CAN_READ,
        CAN_CREATE.flags(), CAN_CREATE,
        CAN_UPDATE.flags(), CAN_UPDATE,
        CAN_DELETE.flags(), CAN_DELETE
    );

    private       Integer flags;
    private final String  name;

    //region Constructors

    public Permissions() {
        this(NONE);
    }

    public Permissions(Integer flags) {
        this.flags = flags;
        this.name  = null;
    }

    public Permissions(Permissions... permissions) {
        this(0);

        add(permissions);
    }

    private Permissions(String name, Integer flags) {
        this.name  = name;
        this.flags = flags;
    }

    private Permissions(String name, Permissions... permissions) {
        this(name, 0);

        add(permissions);
    }

    public static Permissions of() {
        return of(NONE);
    }

    public static Permissions of(Integer flags) {
        return new Permissions(flags);
    }

    public static Permissions of(Permissions... permissions) {
        return new Permissions(permissions);
    }

    private static Permissions of(String name, Integer flags) {
        return new Permissions(name, flags);
    }

    private static Permissions of(String name, Permissions... permissions) {
        return new Permissions(name, permissions);
    }

    public static Permissions find(String... names) {
        return Permissions.of(Arrays.stream(names)
                                    .map(permissionsNameMap::get)
                                    .filter(Objects::nonNull)
                                    .toArray(Permissions[]::new));
    }

    //endregion Constructors

    //region Data

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

    public boolean has(Permissions requiredPermissions) {
        return requiredPermissions.equals(NONE) || (flags & requiredPermissions.flags()) > 0;
    }

    public Permissions clear() {
        flags = NONE.flags();

        return this;
    }

    private String name() {
        return name;
    }

    public Integer flags() {
        return flags;
    }

    public Set<String> names() {
        if (Objects.equals(flags, NONE.flags()))
            return Set.of(NONE.name());

        return permissionsFlagMap.values()
                                 .stream()
                                 .filter(this::has)
                                 .map(Permissions::name)
                                 .collect(Collectors.toSet());
    }

    public String[] namesArray() {
        return names().toArray(String[]::new);
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
