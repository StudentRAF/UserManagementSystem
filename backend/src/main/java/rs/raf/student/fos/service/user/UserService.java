package rs.raf.student.fos.service.user;

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
import rs.raf.student.fos.dto.user.UserCreateDto;
import rs.raf.student.fos.dto.user.UserGetDto;
import rs.raf.student.fos.dto.user.UserLoginDto;
import rs.raf.student.fos.dto.user.UserUpdateDto;
import rs.raf.student.fos.entity.User;
import rs.raf.student.fos.exception.FOSException;
import rs.raf.student.fos.mapper.UserMapper;
import rs.raf.student.fos.repository.IUserRepository;
import rs.raf.student.fos.type.AuthenticationSchemeType;
import rs.raf.student.fos.utilities.JwtUtilities;

import static rs.raf.student.fos.exception.ExceptionType.DELETE_USER_NOT_FOUND_ID;
import static rs.raf.student.fos.exception.ExceptionType.FIND_USER_NOT_FOUND_ID;
import static rs.raf.student.fos.exception.ExceptionType.GENERATE_AUTHORIZATION_TOKEN_NOT_FOUND_AUTHENTICATED_USER;
import static rs.raf.student.fos.exception.ExceptionType.UPDATE_USER_NOT_FOUND_ID;

@Service
@RequiredArgsConstructor
@ExtensionMethod({UserMapper.class})
public class UserService implements IUserService {

    private final IUserRepository       repository;
    private final AuthenticationManager authenticationManager;

    @Override
    public Page<UserGetDto> findAll(Pageable pageable) throws FOSException {
        return repository.findAll(pageable)
                         .map(UserMapper::mapDto);
    }

    @Override
    public UserGetDto findById(Long id) throws FOSException {
        return repository.findById(id)
                         .orElseThrow(() -> new FOSException(FIND_USER_NOT_FOUND_ID, id.toString()))
                         .mapDto();
    }

    @Override
    public UserGetDto login(UserLoginDto loginDto) throws FOSException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ((User) authentication.getPrincipal()).mapDto();

    }

    @Override
    public UserGetDto create(UserCreateDto createDto) throws FOSException {
        return repository.save(createDto.mapEntity())
                         .mapDto();
    }

    @Override
    public UserGetDto update(Long id, UserUpdateDto updateDto) throws FOSException {
        return repository.save(repository.findById(id)
                                         .orElseThrow(() -> new FOSException(UPDATE_USER_NOT_FOUND_ID, id.toString()))
                                         .map(updateDto))
                         .mapDto();
    }

    @Override
    public Long delete(Long id) throws FOSException {
        repository.delete(repository.findById(id)
                                    .orElseThrow(() -> new FOSException(DELETE_USER_NOT_FOUND_ID, id.toString())));

        return id;
    }

    @Override
    public String authorizationToken() throws FOSException {
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
            SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User authenticatedUser)
            return AuthenticationSchemeType.BEARER.addCredentials(JwtUtilities.generateToken(authenticatedUser));

        throw new FOSException(GENERATE_AUTHORIZATION_TOKEN_NOT_FOUND_AUTHENTICATED_USER);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                         .orElseThrow(() -> new UsernameNotFoundException("Could not find User. User with email: " + email + " does not exist."));
    }

}
