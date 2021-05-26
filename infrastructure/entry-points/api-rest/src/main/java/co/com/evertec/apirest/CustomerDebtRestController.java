package co.com.evertec.apirest;

import co.com.evertec.CustomerDebtsLog;
import co.com.evertec.model.CustomerDebt;
import co.com.evertec.model.common.Constants;
import co.com.evertec.usecase.CustomerDebtUseCase;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/customer-debts")
@Api(value = "CustomerDebt", tags = {"CustomerDebt"})
public class CustomerDebtRestController {

    private final CustomerDebtUseCase customerDebtUseCase;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {
        Map<String, Object> response = customerDebtUseCase.getAll();
        CustomerDebtsLog
                .logInfoMessage(this.getClass(), String.format(Constants.INFO_MESSAGE_RESPONSE, response.toString()));

        if (!response.isEmpty()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> getById(@Valid @PathVariable long id) {
        Map<String, Object> response = customerDebtUseCase.getById(id);
        CustomerDebtsLog
                .logInfoMessage(this.getClass(), String.format(Constants.INFO_MESSAGE_RESPONSE, response.toString()));
        if (!response.isEmpty()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/customer/identifier/{customerIdentifier}")
    public ResponseEntity<Map<String, Object>> getByCustomerIdentifier(@Valid @PathVariable String customerIdentifier) {
        Map<String, Object> response = customerDebtUseCase.getByCustomerIdentifier(customerIdentifier);
        CustomerDebtsLog
                .logInfoMessage(this.getClass(), String.format(Constants.INFO_MESSAGE_RESPONSE, response.toString()));
        if (!response.isEmpty()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody CustomerDebt customerDebt) {
        CustomerDebtsLog.logInfoMessage(this.getClass(), String.format(Constants.INFO_MESSAGE_REQUEST,
                customerDebt.toString()));
        Map<String, Object> response = customerDebtUseCase.saveOrUpdate(customerDebt, false);
        CustomerDebtsLog
                .logInfoMessage(this.getClass(), String.format(Constants.INFO_MESSAGE_RESPONSE, response.toString()));

        if (!response.isEmpty()) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody CustomerDebt customerDebt) {
        CustomerDebtsLog
                .logInfoMessage(this.getClass(),
                        String.format(Constants.INFO_MESSAGE_REQUEST, customerDebt.toString()));
        Map<String, Object> response = customerDebtUseCase.saveOrUpdate(customerDebt, true);
        CustomerDebtsLog
                .logInfoMessage(this.getClass(), String.format(Constants.INFO_MESSAGE_RESPONSE, response.toString()));

        if (!response.isEmpty()) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> delete(@Valid @PathVariable long id) {
        Map<String, Object> response = customerDebtUseCase.delete(id);
        CustomerDebtsLog
                .logInfoMessage(this.getClass(), String.format(Constants.INFO_MESSAGE_RESPONSE, response.toString()));
        if (!response.isEmpty()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
