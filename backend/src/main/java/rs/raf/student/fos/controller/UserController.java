package rs.raf.student.fos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rs.raf.student.fos.dto.user.UserCreateDto;
import rs.raf.student.fos.dto.user.UserGetDto;
import rs.raf.student.fos.dto.user.UserLoginDto;
import rs.raf.student.fos.dto.user.UserUpdateDto;
import rs.raf.student.fos.exception.ExceptionUtils;
import rs.raf.student.fos.service.user.UserService;

import static rs.raf.student.fos.configuration.FOSConfiguration.Controller.Endpoint.User;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping(User.GET_ALL)
    public ResponseEntity<Page<UserGetDto>> getAll(Pageable pageable) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK));
    }

    @GetMapping(User.GET_ONE)
    public ResponseEntity<UserGetDto> getById(@PathVariable("id") Long id) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(service.findById(id), HttpStatus.OK));
    }

    @PostMapping(User.LOGIN)
    public ResponseEntity<UserGetDto> login(@RequestBody @Valid UserLoginDto loginDto) {
        return ExceptionUtils.handleResponse(() -> {
            UserGetDto userGetDto = service.login(loginDto);
            String     token      = service.authorizationToken();

            return ResponseEntity.status(HttpStatus.OK)
                                 .header(HttpHeaders.AUTHORIZATION, token)
                                 .body(userGetDto);
        });
    }

    @PostMapping(User.CREATE)
    public ResponseEntity<UserGetDto> create(@RequestBody @Valid UserCreateDto createDto) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(service.create(createDto), HttpStatus.CREATED));
    }

    @PutMapping(User.UPDATE)
    public ResponseEntity<UserGetDto> update(@PathVariable("id") Long id, @RequestBody @Valid UserUpdateDto updateDto) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(service.update(id, updateDto), HttpStatus.OK));
    }

    @DeleteMapping(User.DELETE)
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(service.delete(id), HttpStatus.OK));
    }


}
