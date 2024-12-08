package rs.raf.student.ums.service.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import rs.raf.student.ums.dto.user.UserCreateDto;
import rs.raf.student.ums.dto.user.UserGetDto;
import rs.raf.student.ums.dto.user.UserLoginDto;
import rs.raf.student.ums.dto.user.UserUpdateDto;
import rs.raf.student.ums.exception.UMSException;

public interface IUserService extends UserDetailsService {

    Page<UserGetDto> findAll(Pageable pageable) throws UMSException;

    UserGetDto findById(Long id) throws UMSException;

    UserGetDto login(UserLoginDto loginDto) throws UMSException;

    UserGetDto create(UserCreateDto createDto) throws UMSException;

    UserGetDto update(Long id, UserUpdateDto updateDto) throws UMSException;

    Long delete(Long id) throws UMSException;

    String authorizationToken() throws UMSException;

}
