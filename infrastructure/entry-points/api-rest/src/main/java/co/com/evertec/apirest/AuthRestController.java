package co.com.evertec.apirest;

import co.com.evertec.CustomerDebtsLog;
import co.com.evertec.model.Authentication;
import co.com.evertec.model.common.Constants;
import co.com.evertec.usecase.AuthUseCase;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "auth")
@Api(value = "Authentication", tags = {"Authentication"})
public class AuthRestController {

    private final AuthUseCase authUseCase;

    @PostMapping(value = "/sig-in")
    public ResponseEntity<Map<String, Object>> authentication(@Valid @RequestBody Authentication authentication) {
        Map<String, Object> result = authUseCase.authentication(authentication);

        if (!result.isEmpty()) {
            CustomerDebtsLog.logInfoMessage(AuthRestController.class,
                    String.format(Constants.INFO_MESSAGE_RESPONSE, result.toString()));

            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
