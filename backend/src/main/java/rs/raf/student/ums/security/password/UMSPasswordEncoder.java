package rs.raf.student.ums.security.password;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rs.raf.student.ums.utilities.PasswordUtilities;

import java.util.Objects;

@Data
@Component
@RequiredArgsConstructor
public class UMSPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return PasswordUtilities.encodeBase64(DigestUtils.sha256(rawPassword + ""));
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return Objects.equals(encode(rawPassword), encodedPassword);
    }

}
