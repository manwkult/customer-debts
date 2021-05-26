package co.com.evertec.usecase.util;

import java.util.HashMap;
import java.util.Map;

public class Util {
    private Util() {}

    public static Map<String, Object> responseResult(int success, String code, String message, Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("code", code);
        result.put("message", message);
        result.put("data", data);

        return result;
    }
}
