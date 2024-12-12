package rs.raf.student.fos.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.MessageFormat;
import java.util.Objects;

public class UserLoginDto {

    @Email
    @NotBlank
    @Size(min = 1, max = 256)
    @JsonProperty(Meta.Property.EMAIL)
    private String email;

    @NotBlank
    @Size(min = 8, max = 64)
    @JsonProperty(Meta.Property.PASSWORD)
    private String password;

    //region Constructors

    public UserLoginDto() { }

    public UserLoginDto(String password, String email) {
        setPassword(password);
        setEmail(email);
    }

    public static UserLoginDto of() {
        return new UserLoginDto();
    }

    public static UserLoginDto of(String password, String email) {
        return new UserLoginDto(password, email);
    }

    //endregion Constructors

    //region Data

    public UserLoginDto setEmail(String email) {
        this.email = email;

        return this;
    }

    public String email() {
        return email;
    }

    public UserLoginDto setPassword(String password) {
        this.password = password;

        return this;
    }

    public String password() {
        return password;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof UserLoginDto dto)
            return Objects.equals(dto.email, email) &&
                   Objects.equals(dto.password, password);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    public String toString() {
        return MessageFormat.format("""
                                    {0}: '{' email = {1} | password = {2} '}'\
                                    """,
                                    UserLoginDto.class.getSimpleName(), email, password);
    }

    //endregion Object

    //region Meta

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Meta {

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Property {

            public static final String EMAIL         = "email";
            public static final String PASSWORD      = "password";

        }

    }

    //endregion Meta

}
