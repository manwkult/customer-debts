package co.com.evertec.postgres.exception;

import co.com.evertec.CustomerDebtsLog;
import co.com.evertec.model.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RepositoryResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex,
            WebRequest request) {
        String code = Constants.CD_003;
        String message = ex.getMostSpecificCause().getMessage();

        Map<String, Object> responseError = new HashMap<>();

        responseError.put("success", 0);
        responseError.put("code", code);
        responseError.put("message", message);
        responseError.put("data", null);

        CustomerDebtsLog.logErrorMessage(this.getClass(),
                String.format(Constants.INFO_MESSAGE_CODE, code) +
                        Constants.INFO_MESSAGE_SEPARATOR +
                        String.format(Constants.INFO_MESSAGE_MESSAGE, message));

        return new ResponseEntity<>(responseError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolationException(
            ConstraintViolationException ex,
            WebRequest request) {
        String code = Constants.CD_004;
        String message = ex.getConstraintViolations().toString();

        Map<String, Object> responseError = new HashMap<>();

        responseError.put("success", 0);
        responseError.put("code", code);
        responseError.put("message", message);
        responseError.put("data", null);

        CustomerDebtsLog.logErrorMessage(this.getClass(),
                String.format(Constants.INFO_MESSAGE_CODE, code) +
                        Constants.INFO_MESSAGE_SEPARATOR +
                        String.format(Constants.INFO_MESSAGE_MESSAGE, message));

        return new ResponseEntity<>(responseError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
