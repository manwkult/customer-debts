package co.com.evertec.model.gateway;

import co.com.evertec.model.security.Authorities;
import co.com.evertec.model.security.User;

import java.util.Collection;

public interface JWTGateway {
    String create(User user, Collection<Authorities> authorities);
}
