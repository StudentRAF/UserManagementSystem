package rs.raf.student.fos.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import rs.raf.student.fos.type.Permissions;

import java.text.MessageFormat;
import java.util.Objects;

public class UserUpdateDto {

    @NotBlank
    @Size(max = 32)
    @JsonProperty(Meta.Property.FIRST_NAME)
    private String firstName;

    @NotBlank
    @Size(max = 32)
    @JsonProperty(Meta.Property.LAST_NAME)
    private String lastName;

    @Email
    @NotBlank
    @Size(min = 1, max = 256)
    @JsonProperty(Meta.Property.EMAIL)
    private String email;

    @NotNull
    @JsonProperty(Meta.Property.PERMISSIONS)
    private Permissions permissions;

    //region Constructors

    public UserUpdateDto() { }

    public UserUpdateDto(String firstName, String lastName, String email, Permissions permissions) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPermissions(permissions);
    }

    public static UserUpdateDto of() {
        return new UserUpdateDto();
    }

    public static UserUpdateDto of(String firstName, String lastName, String email, Permissions permissions) {
        return new UserUpdateDto(firstName, lastName, email, permissions);
    }

    //endregion Constructors

    //region Data

    public UserUpdateDto setFirstName(String firstName) {
        this.firstName = firstName;

        return this;
    }

    public String firstName() {
        return firstName;
    }

    public UserUpdateDto setLastName(String lastName) {
        this.lastName = lastName;

        return this;
    }

    public String lastName() {
        return lastName;
    }

    public UserUpdateDto setEmail(String email) {
        this.email = email;

        return this;
    }

    public String email() {
        return email;
    }

    public UserUpdateDto setPermissions(Permissions permissions) {
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

        if (object instanceof UserUpdateDto dto)
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
                                    UserUpdateDto.class.getSimpleName(), firstName, lastName, email, permissions);
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
