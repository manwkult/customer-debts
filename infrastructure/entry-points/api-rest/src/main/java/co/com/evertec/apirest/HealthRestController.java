package co.com.evertec.apirest;

import co.com.evertec.model.common.Constants;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "health")
@Api(value = "Health", tags = {"Health"})
public class HealthRestController {

    @GetMapping
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", 1);
        response.put("code", Constants.CD_000);
        response.put("data", null);
        response.put("message", "Ok");
        return ResponseEntity.ok(response);
    }
}
