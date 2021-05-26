package co.com.evertec.postgres.dao;

import co.com.evertec.postgres.entity.CustomerDebtEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDebtDAO extends CrudRepository<CustomerDebtEntity, Long> {
    List<CustomerDebtEntity> getByCustomerIdentifier(String customerIdentifier);
}
