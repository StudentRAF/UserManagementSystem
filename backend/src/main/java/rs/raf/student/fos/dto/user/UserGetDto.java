package rs.raf.student.fos.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import rs.raf.student.fos.type.Permissions;

import java.text.MessageFormat;
import java.util.Objects;

public class UserGetDto {

    @JsonProperty(Meta.Property.FIRST_NAME)
    private String firstName;

    @JsonProperty(Meta.Property.LAST_NAME)
    private String lastName;

    @JsonProperty(Meta.Property.EMAIL)
    private String email;

    @JsonProperty(Meta.Property.PERMISSIONS)
    private Permissions permissions;

    //region Constructors

    public UserGetDto() { }

    public UserGetDto(String firstName, String lastName, String email, Permissions permissions) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPermissions(permissions);
    }

    public static UserGetDto of() {
        return new UserGetDto();
    }

    public static UserGetDto of(String firstName, String lastName, String email, Permissions permissions) {
        return new UserGetDto(firstName, lastName, email, permissions);
    }

    //endregion Constructors

    //region Data

    public UserGetDto setFirstName(String firstName) {
        this.firstName = firstName;

        return this;
    }

    public String firstName() {
        return firstName;
    }

    public UserGetDto setLastName(String lastName) {
        this.lastName = lastName;

        return this;
    }

    public String lastName() {
        return lastName;
    }

    public UserGetDto setEmail(String email) {
        this.email = email;

        return this;
    }

    public String email() {
        return email;
    }

    public UserGetDto setPermissions(Permissions permissions) {
        this.permissions = permissions;

        return this;
    }

    public Permissions permissions() {
        return permissions;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof UserGetDto dto)
            return Objects.equals(dto.email, email)         &&
                   Objects.equals(dto.lastName, lastName)   &&
                   Objects.equals(dto.firstName, firstName) &&
                   Objects.equals(dto.permissions, permissions);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, lastName, firstName, permissions);
    }

    public String toString() {
        return MessageFormat.format("""
                                    {0}: '{' firstName = {1} | lastName = {2} | email = {3} | permissions = {4} '}'\
                                    """,
                                    UserGetDto.class.getSimpleName(), firstName, lastName, email, permissions);
    }

    //endregion Object

    //region Meta

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Meta {

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Property {

            public static final String FIRST_NAME    = "first_name";
            public static final String LAST_NAME     = "last_name";
            public static final String EMAIL         = "email";
            public static final String PERMISSIONS   = "permissions";

        }

    }

    //endregion Meta

}
