package rs.raf.student.fos.service.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import rs.raf.student.fos.dto.user.UserCreateDto;
import rs.raf.student.fos.dto.user.UserGetDto;
import rs.raf.student.fos.dto.user.UserLoginDto;
import rs.raf.student.fos.dto.user.UserUpdateDto;
import rs.raf.student.fos.exception.FOSException;

public interface IUserService extends UserDetailsService {

    Page<UserGetDto> findAll(Pageable pageable) throws FOSException;

    UserGetDto findById(Long id) throws FOSException;

    UserGetDto login(UserLoginDto loginDto) throws FOSException;

    UserGetDto create(UserCreateDto createDto) throws FOSException;

    UserGetDto update(Long id, UserUpdateDto updateDto) throws FOSException;

    Long delete(Long id) throws FOSException;

    String authorizationToken() throws FOSException;

}
