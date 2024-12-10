package rs.raf.student.ums.service.user;

import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.raf.student.ums.dto.user.UserCreateDto;
import rs.raf.student.ums.dto.user.UserGetDto;
import rs.raf.student.ums.dto.user.UserLoginDto;
import rs.raf.student.ums.dto.user.UserUpdateDto;
import rs.raf.student.ums.entity.User;
import rs.raf.student.ums.exception.UMSException;
import rs.raf.student.ums.mapper.UserMapper;
import rs.raf.student.ums.repository.IUserRepository;
import rs.raf.student.ums.type.AuthenticationSchemeType;
import rs.raf.student.ums.utilities.JwtUtilities;

import static rs.raf.student.ums.exception.ExceptionType.DELETE_USER_NOT_FOUND_ID;
import static rs.raf.student.ums.exception.ExceptionType.FIND_USER_NOT_FOUND_ID;
import static rs.raf.student.ums.exception.ExceptionType.GENERATE_AUTHORIZATION_TOKEN_NOT_FOUND_AUTHENTICATED_USER;
import static rs.raf.student.ums.exception.ExceptionType.UPDATE_USER_NOT_FOUND_ID;

@Service
@RequiredArgsConstructor
@ExtensionMethod({UserMapper.class})
public class UserService implements IUserService {

    private final IUserRepository       repository;
    private final AuthenticationManager authenticationManager;

    @Override
    public Page<UserGetDto> findAll(Pageable pageable) throws UMSException {
        return repository.findAll(pageable)
                         .map(UserMapper::mapDto);
    }

    @Override
    public UserGetDto findById(Long id) throws UMSException {
        return repository.findById(id)
                         .orElseThrow(() -> new UMSException(FIND_USER_NOT_FOUND_ID, id.toString()))
                         .mapDto();
    }

    @Override
    public UserGetDto login(UserLoginDto loginDto) throws UMSException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ((User) authentication.getPrincipal()).mapDto();

    }

    @Override
    public UserGetDto create(UserCreateDto createDto) throws UMSException {
        return repository.save(createDto.mapEntity())
                         .mapDto();
    }

    @Override
    public UserGetDto update(Long id, UserUpdateDto updateDto) throws UMSException {
        return repository.save(repository.findById(id)
                                         .orElseThrow(() -> new UMSException(UPDATE_USER_NOT_FOUND_ID, id.toString()))
                                         .map(updateDto))
                         .mapDto();
    }

    @Override
    public Long delete(Long id) throws UMSException {
        repository.delete(repository.findById(id)
                                    .orElseThrow(() -> new UMSException(DELETE_USER_NOT_FOUND_ID, id.toString())));

        return id;
    }

    @Override
    public String authorizationToken() throws UMSException {
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
            SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User authenticatedUser)
            return AuthenticationSchemeType.BEARER.addCredentials(JwtUtilities.generateToken(authenticatedUser));

        throw new UMSException(GENERATE_AUTHORIZATION_TOKEN_NOT_FOUND_AUTHENTICATED_USER);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                         .orElseThrow(() -> new UsernameNotFoundException("Could not find User. User with email: " + email + " does not exist."));
    }

}
