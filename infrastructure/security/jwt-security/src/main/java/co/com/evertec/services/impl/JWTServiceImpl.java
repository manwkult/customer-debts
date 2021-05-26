package co.com.evertec.services.impl;

import co.com.evertec.model.common.Constants;
import co.com.evertec.services.JWTService;
import co.com.evertec.mixin.SimpleGrantedAuthorityMixin;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

@Service
public class JWTServiceImpl implements JWTService {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Override
    public boolean validate(String token) throws MalformedJwtException, SignatureException, UnsupportedJwtException,
            ExpiredJwtException {
        return getClaims(token) != null;
    }

    @Override
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    @Override
    public Collection<GrantedAuthority> getRoles(String token) throws IOException {
        Object roles = getClaims(token).get(Constants.JWT_AUTHORITIES);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        SimpleGrantedAuthority[] arrayJsonAuthorities = mapper
                .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
                .readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class);

        return Arrays.asList(arrayJsonAuthorities);
    }

    public String resolve(String token) {
        if (token != null) {
            if (token.startsWith("Bearer ")) {
                return token.replace("Bearer ", "");
            } else {
                return token;
            }
        }

        return null;
    }

    public Claims getClaims(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(resolve(jwtToken))
                .getBody();
    }
}
