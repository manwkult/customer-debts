package co.com.evertec.postgres.service;

import co.com.evertec.postgres.dao.CustomerDebtDAO;
import co.com.evertec.postgres.entity.CustomerDebtEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CustomerDebtServiceTest {

    @InjectMocks
    CustomerDebtService customerDebtService;

    @Mock
    CustomerDebtDAO customerDebtDAO;

    @Test
    public void testGetAll() {
        Iterable<CustomerDebtEntity> customerDebtEntities = new ArrayList<>();
        doReturn(customerDebtEntities).when(customerDebtDAO).findAll();
        assertEquals(ArrayList.class, customerDebtService.getAll().getClass());
        verify(customerDebtDAO, times(1)).findAll();
    }

    @Test
    public void testGetById() {
        CustomerDebtEntity customerDebtEntity = new CustomerDebtEntity();
        doReturn(Optional.of(new CustomerDebtEntity())).when(customerDebtDAO).findById(anyLong());
        assertEquals(CustomerDebtEntity.class, customerDebtService.getById(anyLong()).getClass());
        verify(customerDebtDAO, times(1)).findById(anyLong());
    }

    @Test
    public void testSaveOrUpdate() {
        CustomerDebtEntity customerDebtEntity = new CustomerDebtEntity();
        customerDebtEntity.setId(1L);
        customerDebtEntity.setCustomerIdentifier("test");
        customerDebtEntity.setName("test");
        customerDebtEntity.setEmail("test");
        customerDebtEntity.setAmount(new BigDecimal(100));
        customerDebtEntity.setExpirationDate(new Date());

        doReturn(customerDebtEntity).when(customerDebtDAO).save(any());
        assertEquals(CustomerDebtEntity.class, customerDebtService.saveOrUpdate(customerDebtEntity).getClass());
        verify(customerDebtDAO, times(1)).save(any());
    }

    @Test
    public void testDelete() {
        doNothing().when(customerDebtDAO).deleteById(anyLong());
        assertTrue(customerDebtService.delete(anyLong()));
        verify(customerDebtDAO, times(1)).deleteById(anyLong());
    }
}
