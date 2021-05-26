package co.com.evertec.adapter;

import co.com.evertec.CustomerDebtsLog;
import co.com.evertec.model.common.Constants;
import co.com.evertec.model.gateway.JWTGateway;
import co.com.evertec.model.security.Authorities;
import co.com.evertec.model.security.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

@Component
public class JWTAdapter implements JWTGateway {

    @Value("${jwt.secret.key}")
    private String secretKey;

    public String create(User user, Collection<Authorities> authorities) {
        Collection<? extends GrantedAuthority> grantedAuthorities = (Collection) authorities;
        Claims claims = Jwts.claims();

        try {
            claims.put(Constants.JWT_AUTHORITIES, new ObjectMapper().writeValueAsString(grantedAuthorities));
        } catch (JsonProcessingException e) {
            CustomerDebtsLog.logErrorMessage(this.getClass(),
                    String.format(Constants.INFO_MESSAGE_CODE, Constants.CD_005) +
                            Constants.INFO_MESSAGE_SEPARATOR +
                            String.format(Constants.INFO_MESSAGE_MESSAGE, e.getMessage()));
        }

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Constants.JWT_TIME_EXPIRATION))
                .compact();
    }
}
