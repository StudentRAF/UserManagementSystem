package rs.raf.student.ums.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import rs.raf.student.ums.converter.PermissionConverter;
import rs.raf.student.ums.type.Permissions;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(
    name = User.Meta.Table.NAME,
    indexes = {
        @Index(
            name = "index_user_on_email",
            columnList = User.Meta.Column.EMAIL
        ),
    },
    uniqueConstraints = {
        @UniqueConstraint(
            name = "unique_user_on_email",
            columnNames = User.Meta.Column.EMAIL
        ),
    }
)
public class User implements UserDetails {

    @Id
    @Column(name = Meta.Column.IDENTIFIER)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Meta.Column.FIRST_NAME, nullable = false, length = 32)
    private String firstName;

    @Column(name = Meta.Column.LAST_NAME, nullable = false, length = 32)
    private String lastName;

    @Column(name = Meta.Column.EMAIL, nullable = false)
    private String email;

    @Column(name = Meta.Column.PASSWORD, nullable = false, length = 44)
    private String password;

    @Convert(converter = PermissionConverter.class)
    @Column(name = Meta.Column.PERMISSIONS, nullable = false)
    private Permissions permissions;

    @CreatedDate
    @Column(name = Meta.Column.CREATED_AT, nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = Meta.Column.MODIFIED_AT, nullable = false)
    private LocalDateTime modifiedAt;

    //region Constructors

    public User() { }

    public User(String firstName, String lastName, String email, String password, Permissions permissions) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
        setPermissions(permissions);
    }

    public static User of() {
        return new User();
    }

    public static User of(String firstName, String lastName, String email, String password, Permissions permissions) {
        return new User(firstName, lastName, email, password, permissions);
    }

    //endregion Constructors

    //region Data

    public Long id() {
        return id;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;

        return this;
    }

    public String firstName() {
        return firstName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;

        return this;
    }

    public String lastName() {
        return lastName;
    }

    public User setEmail(String email) {
        this.email = email;

        return this;
    }

    public String email() {
        return email;
    }

    public User setPassword(String password) {
        this.password = password;

        return this;
    }

    public String password() {
        return password;
    }

    public User setPermissions(Permissions permissions) {
        this.permissions = permissions;

        return this;
    }

    public Permissions permissions() {
        return permissions;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime modifiedAt() {
        return modifiedAt;
    }

    //endregion Data
    
    //region UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions.names()
                          .stream()
                          .map(SimpleGrantedAuthority::new)
                          .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    //endregion UserDetails

    //region Object

    public boolean equals(final Object object) {
        if (object == this)
            return true;

        if (object instanceof User user)
            return Objects.equals(user.id, id)               &&
                   Objects.equals(user.email, email)         &&
                   Objects.equals(user.lastName, lastName)   &&
                   Objects.equals(user.password, password)   &&
                   Objects.equals(user.firstName, firstName) &&
                   Objects.equals(user.permissions, permissions);

        return false;
    }

    public int hashCode() {
        return Objects.hash(id, email, lastName, password, firstName, permissions);
    }

    public String toString() {
        return MessageFormat.format("""
                                    {0}: '{' id = {1} | firstName = {2} | lastName = {3} | email = {4} | password = {5} | permissions = {6} \
                                    createdAt = {7} | modifiedAt = {8} '}'\
                                    """,
                                    User.class.getSimpleName(), id, firstName, lastName, email, password, permissions, createdAt, modifiedAt);
    }
    
    //endregion Object
    
    //region Meta

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Meta {

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Table {

            public static final String NAME = "\"user\"";

        }

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Column {

            public static final String IDENTIFIER    = "id";
            public static final String FIRST_NAME    = "first_name";
            public static final String LAST_NAME     = "last_name";
            public static final String EMAIL         = "email";
            public static final String PASSWORD      = "password";
            public static final String PERMISSIONS   = "permissions";
            public static final String CREATED_AT    = "created_at";
            public static final String MODIFIED_AT   = "modified_at";

        }

    }

    //endregion Meta

}
