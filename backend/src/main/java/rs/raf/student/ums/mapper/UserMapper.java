package rs.raf.student.ums.mapper;

import rs.raf.student.ums.dto.user.UserCreateDto;
import rs.raf.student.ums.dto.user.UserGetDto;
import rs.raf.student.ums.dto.user.UserUpdateDto;
import rs.raf.student.ums.entity.User;
import rs.raf.student.ums.utilities.PasswordUtilities;

public class UserMapper {

    public static User map(User user, UserUpdateDto updateDto) {
        return user.setFirstName(updateDto.firstName())
                   .setLastName(updateDto.lastName())
                   .setEmail(updateDto.email())
                   .setPermissions(updateDto.permissions());
    }

    public static User map(User user, UserCreateDto createDto) {
        return user.setFirstName(createDto.firstName())
                   .setLastName(createDto.lastName())
                   .setEmail(createDto.email())
                   .setPassword(PasswordUtilities.hashPassword(createDto.password(), ""))
                   .setPermissions(createDto.permissions());
    }

    public static User mapEntity(UserCreateDto createDto) {
        return map(User.of(), createDto);
    }

    public static UserGetDto mapDto(User user) {
        return UserGetDto.of(user.firstName(), user.lastName(), user.email(), user.permissions());
    }

}
