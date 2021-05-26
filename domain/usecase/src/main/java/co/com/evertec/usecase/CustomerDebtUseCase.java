package co.com.evertec.usecase;

import co.com.evertec.model.CustomerDebt;
import co.com.evertec.model.common.Constants;
import co.com.evertec.model.gateway.CustomerDebtGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static co.com.evertec.usecase.util.Util.responseResult;

@RequiredArgsConstructor
public class CustomerDebtUseCase {

    private final CustomerDebtGateway customerDebtGateway;
    private final MessageSource messageSource;

    public Map<String, Object> getAll() {
        List<CustomerDebt> data = customerDebtGateway.getAll();
        boolean ok = data != null && !data.isEmpty();

        return responseResult(ok ? 1 : 0,
                Constants.CD_000,
                messageSource.getMessage(ok ? "customer.debt.results" : "customer.debt.not.results",
                        new Object[]{},
                        Locale.getDefault()),
                ok ? data : null);
    }

    public Map<String, Object> getById(long id) {
        CustomerDebt data = customerDebtGateway.getById(id);
        boolean ok = data.getId() != 0;

        return responseResult(ok ? 1 : 0,
                Constants.CD_000,
                messageSource.getMessage(ok ? "customer.debt.results" : "customer.debt.not.results",
                        new Object[]{},
                        Locale.getDefault()),
                ok ? data : null);
    }

    public Map<String, Object> getByCustomerIdentifier(String customerIdentifier) {
        List<CustomerDebt> data = customerDebtGateway.getByCustomerIdentifier(customerIdentifier);
        boolean ok = data != null && !data.isEmpty();

        return responseResult(ok ? 1 : 0,
                Constants.CD_000,
                messageSource.getMessage(ok ? "customer.debt.results" : "customer.debt.not.results",
                        new Object[]{},
                        Locale.getDefault()),
                ok ? data : null);
    }

    public Map<String, Object> saveOrUpdate(CustomerDebt customerDebt, boolean update) {
        if (update) {
            CustomerDebt customerDebtExist = customerDebtGateway.getById(customerDebt.getId());
            if (customerDebtExist == null || customerDebtExist.getId() == 0) {
                return responseResult(0,
                        Constants.CD_000,
                        messageSource.getMessage("customer.debt.not.found",
                                new Object[]{customerDebtExist.getId()},
                                Locale.getDefault()),
                        null);
            }
        }

        CustomerDebt data = customerDebtGateway.saveOrUpdate(customerDebt);

        boolean ok = data != null;

        return responseResult(ok ? 1 : 0,
                Constants.CD_000,
                messageSource.getMessage(ok ?
                                update ? "customer.debt.update.successfully" : "customer.debt.create.successfully" :
                                update ? "customer.debt.update.fail" : "customer.debt.create.fail",
                        new Object[]{},
                        Locale.getDefault()),
                ok ? data : null);
    }

    public Map<String, Object> delete(long id) {
        boolean ok = customerDebtGateway.delete(id);
        return responseResult(ok ? 1 : 0,
                Constants.CD_000,
                messageSource.getMessage(ok ? "customer.debt.delete.successfully" : "customer.debt.delete.fail",
                        new Object[]{id},
                        Locale.getDefault()),
                null);
    }
}
