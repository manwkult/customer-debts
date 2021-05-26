package co.com.evertec.postgres.service;

import co.com.evertec.postgres.dao.CustomerDebtDAO;
import co.com.evertec.postgres.entity.CustomerDebtEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerDebtService {

    @Autowired
    CustomerDebtDAO customerDebtDAO;

    @Cacheable(value = "CustomerDebt", key = "'All'")
    public Iterable<CustomerDebtEntity> getAll() {
        return customerDebtDAO.findAll();
    }

    @Cacheable(value = "CustomerDebt", key = "#id")
    public CustomerDebtEntity getById(long id) {
        return customerDebtDAO.findById(id).orElse(null);
    }

    @Cacheable(value = "CustomerDebt", key = "#customerIdentifier")
    public List<CustomerDebtEntity> getByCustomerIdentifier(String customerIdentifier) {
        return customerDebtDAO.getByCustomerIdentifier(customerIdentifier);
    }

    @CacheEvict(value = "CustomerDebt", allEntries = true)
    public CustomerDebtEntity saveOrUpdate(CustomerDebtEntity customerDebtEntity) {
        return customerDebtDAO.save(customerDebtEntity);
    }

    @CacheEvict(value = "CustomerDebt", allEntries = true)
    public boolean delete(long id) {
        customerDebtDAO.deleteById(id);
        return true;
    }
}
