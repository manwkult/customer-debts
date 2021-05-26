package co.com.evertec.postgres.adapter;

import co.com.evertec.model.CustomerDebt;
import co.com.evertec.model.gateway.CustomerDebtGateway;
import co.com.evertec.postgres.entity.CustomerDebtEntity;
import co.com.evertec.postgres.service.CustomerDebtService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class CustomerDebtRepositoryAdapter implements CustomerDebtGateway {

    @Autowired
    CustomerDebtService customerDebtService;

    @Override
    public List<CustomerDebt> getAll() {
        List<CustomerDebt> customerDebts = new ArrayList<>();
        Iterable<CustomerDebtEntity> customerDebtEntities = customerDebtService.getAll();

        if (customerDebtEntities != null) {
            customerDebts = StreamSupport.stream(customerDebtEntities.spliterator(), false)
                    .map(customerDebtEntity -> CustomerDebt.builder()
                            .id(customerDebtEntity.getId())
                            .customerIdentifier(customerDebtEntity.getCustomerIdentifier())
                            .name(customerDebtEntity.getName())
                            .email(customerDebtEntity.getEmail())
                            .amount(customerDebtEntity.getAmount())
                            .debtIdentifier(customerDebtEntity.getDebtIdentifier())
                            .expirationDate(customerDebtEntity.getExpirationDate())
                            .build())
                    .collect(Collectors.toList());
        }

        return customerDebts;
    }

    @Override
    public CustomerDebt getById(long id) {
        CustomerDebt customerDebt = new CustomerDebt();
        CustomerDebtEntity customerDebtEntity = customerDebtService.getById(id);

        if (customerDebtEntity != null) {
            BeanUtils.copyProperties(customerDebtEntity, customerDebt);
        }

        return customerDebt;
    }

    @Override
    public List<CustomerDebt> getByCustomerIdentifier(String customerIdentifier) {
        List<CustomerDebt> customerDebts = new ArrayList<>();
        Iterable<CustomerDebtEntity> customerDebtEntities = customerDebtService.getByCustomerIdentifier(customerIdentifier);

        if (customerDebtEntities != null) {
            customerDebts = StreamSupport.stream(customerDebtEntities.spliterator(), false)
                    .map(customerDebtEntity -> CustomerDebt.builder()
                            .id(customerDebtEntity.getId())
                            .customerIdentifier(customerDebtEntity.getCustomerIdentifier())
                            .name(customerDebtEntity.getName())
                            .email(customerDebtEntity.getEmail())
                            .amount(customerDebtEntity.getAmount())
                            .debtIdentifier(customerDebtEntity.getDebtIdentifier())
                            .expirationDate(customerDebtEntity.getExpirationDate())
                            .build())
                    .collect(Collectors.toList());
        }

        return customerDebts;
    }

    @Override
    public CustomerDebt saveOrUpdate(CustomerDebt customerDebt) {
        CustomerDebtEntity customerDebtEntity = new CustomerDebtEntity();
        BeanUtils.copyProperties(customerDebt, customerDebtEntity);

        customerDebtEntity = customerDebtService.saveOrUpdate(customerDebtEntity);

        if (customerDebtEntity != null) {
            BeanUtils.copyProperties(customerDebtEntity, customerDebt);
            return customerDebt;
        }

        return null;
    }

    @Override
    public boolean delete(long id) {
        CustomerDebtEntity customerDebtExist = customerDebtService.getById(id);
        if (customerDebtExist != null) {
            return customerDebtService.delete(id);
        }

        return false;
    }
}
