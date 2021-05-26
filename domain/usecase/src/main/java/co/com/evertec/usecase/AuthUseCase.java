package co.com.evertec.usecase;

import co.com.evertec.model.Authentication;
import co.com.evertec.model.common.Constants;
import co.com.evertec.model.enumerator.UserEnum;
import co.com.evertec.model.gateway.JWTGateway;
import co.com.evertec.model.security.Authorities;
import co.com.evertec.model.security.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;

import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;

import static co.com.evertec.usecase.util.Util.responseResult;

@RequiredArgsConstructor
public class AuthUseCase {

    private final JWTGateway JWTGateway;
    private final MessageSource messageSource;

    public Map<String, Object> authentication(Authentication authentication) {
        UserEnum user = UserEnum.authentication(authentication.getUsername(), authentication.getPassword());

        if (user != null) {
            Collection<Authorities> authorities = (Collection<Authorities>) Collections
                    .singletonList(Authorities.builder().authority(user.getRole()).build());

            String data = JWTGateway.create(User.builder()
                            .username(user.getUsername())
                            .email(user.getEmail())
                            .build(),
                    authorities);

            return responseResult(1,
                    Constants.CD_000,
                    messageSource.getMessage("user.authenticated",
                            new Object[]{},
                            Locale.getDefault()),
                    data);
        } else {
            return responseResult(0,
                    Constants.CD_000,
                    messageSource.getMessage("user.not.authenticated",
                            new Object[]{},
                            Locale.getDefault()),
                    null);
        }
    }
}
