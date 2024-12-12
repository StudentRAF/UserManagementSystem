package rs.raf.student.fos.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthenticationSchemeType {

    BASIC        ("Basic"),
    BEARER       ("Bearer"),
    DIGEST       ("Digest"),
    DPoP         ("DPoP"),
    GNAP         ("GNAP"),
    HOBA         ("HOBA"),
    MUTUAL       ("Mutual"),
    NEGOTIATE    ("Negotiate"),
    OAUTH        ("OAuth"),
    PRIVATE_TOKEN("PrivateToken"),
    @Deprecated
    SCRAM_SHA_1  ("SCRAM-SHA-1"),
    SCRAM_SHA_256("SCRAM-SHA-256"),
    VAPID        ("vapid");

    private final String name;

    public String addCredentials(String credentials) {
        return name + " " + credentials;
    }

    @Override
    public String toString() {
        return name;
    }

}
