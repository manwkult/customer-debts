package co.com.evertec.services;

import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;
import java.util.Collection;

public interface JWTService {
    boolean validate(String token);
    String getUsername(String token);
    Collection<GrantedAuthority> getRoles(String token) throws IOException;
}
