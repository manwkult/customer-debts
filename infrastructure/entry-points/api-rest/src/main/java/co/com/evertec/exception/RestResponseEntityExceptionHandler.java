package co.com.evertec.exception;

import co.com.evertec.CustomerDebtsLog;
import co.com.evertec.model.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex,
            WebRequest request) {
        String code = Constants.CD_001;
        String message = messageSource.getMessage("field.type.required",
                new Object[]{ex.getName(), ex.getRequiredType().getName(), ex.getValue()},
                Locale.getDefault());

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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String code = Constants.CD_002;
        String message = messageSource.getMessage("method.argument.not.valid",
                new Object[]{},
                Locale.getDefault());

        List<String> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach((FieldError error) ->
                errors.add(messageSource.getMessage("field.required",
                        new Object[]{error.getField(), error.getDefaultMessage()},
                        Locale.getDefault()))
        );

        Map<String, Object> responseError = new HashMap<>();

        responseError.put("success", 0);
        responseError.put("code", code);
        responseError.put("message", message);
        responseError.put("data", errors);

        CustomerDebtsLog.logErrorMessage(this.getClass(),
                String.format(Constants.INFO_MESSAGE_CODE, code) +
                        Constants.INFO_MESSAGE_SEPARATOR +
                        String.format(Constants.INFO_MESSAGE_MESSAGE, message));

        return super.handleExceptionInternal(ex, responseError, headers, status, request);
    }
}
