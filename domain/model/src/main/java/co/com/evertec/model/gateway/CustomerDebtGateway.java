package co.com.evertec.model.gateway;

import co.com.evertec.model.CustomerDebt;

import java.util.List;

public interface CustomerDebtGateway {
    List<CustomerDebt> getAll();
    CustomerDebt getById(long id);
    List<CustomerDebt> getByCustomerIdentifier(String customerIdentifier);
    CustomerDebt saveOrUpdate(CustomerDebt customerDebt);
    boolean delete(long id);
}
