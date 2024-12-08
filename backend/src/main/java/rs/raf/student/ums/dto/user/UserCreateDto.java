package rs.raf.student.ums.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import rs.raf.student.ums.type.Permissions;

import java.text.MessageFormat;
import java.util.Objects;

public class UserCreateDto {

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

    @NotBlank
    @Size(min = 8, max = 64)
    @JsonProperty(Meta.Property.PASSWORD)
    private String password;

    @NotNull
    @JsonProperty(Meta.Property.PERMISSIONS)
    private Permissions permissions;

    //region Constructors

    public UserCreateDto() { }

    public UserCreateDto(String firstName, String lastName, String password, String email, Permissions permissions) {
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        setEmail(email);
        setPermissions(permissions);
    }

    public static UserCreateDto of() {
        return new UserCreateDto();
    }

    public static UserCreateDto of(String firstName, String lastName, String password, String email, Permissions permissions) {
        return new UserCreateDto(firstName, lastName, password, email, permissions);
    }

    //endregion Constructors

    //region Data

    public UserCreateDto setFirstName(String firstName) {
        this.firstName = firstName;

        return this;
    }

    public String firstName() {
        return firstName;
    }

    public UserCreateDto setLastName(String lastName) {
        this.lastName = lastName;

        return this;
    }

    public String lastName() {
        return lastName;
    }

    public UserCreateDto setEmail(String email) {
        this.email = email;

        return this;
    }

    public String email() {
        return email;
    }

    public UserCreateDto setPassword(String password) {
        this.password = password;

        return this;
    }

    public String password() {
        return password;
    }

    public UserCreateDto setPermissions(Permissions permissions) {
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

        if (object instanceof UserCreateDto dto)
            return Objects.equals(dto.email, email)         &&
                   Objects.equals(dto.lastName, lastName)   &&
                   Objects.equals(dto.password, password)   &&
                   Objects.equals(dto.firstName, firstName) &&
                   Objects.equals(dto.permissions, permissions);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, lastName, password, firstName, permissions);
    }

    public String toString() {
        return MessageFormat.format("""
                                    {0}: '{' firstName = {1} | lastName = {2} | email = {3} | password = {4} | permissions = {5} '}'\
                                    """,
                                    UserCreateDto.class.getSimpleName(), firstName, lastName, email, password, permissions);
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
            public static final String PASSWORD      = "password";
            public static final String PERMISSIONS   = "permissions";

        }

    }

    //endregion Meta

}
