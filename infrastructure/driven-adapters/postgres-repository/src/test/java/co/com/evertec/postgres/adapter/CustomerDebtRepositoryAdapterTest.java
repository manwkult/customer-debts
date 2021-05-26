package co.com.evertec.postgres.adapter;

import co.com.evertec.model.CustomerDebt;
import co.com.evertec.postgres.entity.CustomerDebtEntity;
import co.com.evertec.postgres.service.CustomerDebtService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CustomerDebtRepositoryAdapterTest {

    @InjectMocks
    CustomerDebtRepositoryAdapter customerDebtRepositoryAdapter;

    @Mock
    CustomerDebtService customerDebtService;

    CustomerDebtEntity customerDebtEntity;
    List<CustomerDebtEntity> customerDebtEntities;

    @Before
    public void setUp() {
        customerDebtEntity = new CustomerDebtEntity();
        customerDebtEntity.setId(1L);
        customerDebtEntity.setCustomerIdentifier("test");
        customerDebtEntity.setName("test");
        customerDebtEntity.setEmail("test");
        customerDebtEntity.setAmount(new BigDecimal(100));
        customerDebtEntity.setExpirationDate(new Date());

        customerDebtEntities = new ArrayList<>();
        customerDebtEntities.add(customerDebtEntity);
    }

    @Test
    public void testGetAll() {
        doReturn(customerDebtEntities).when(customerDebtService).getAll();

        assertEquals(ArrayList.class, customerDebtRepositoryAdapter.getAll().getClass());

        verify(customerDebtService, times(1)).getAll();
    }

    @Test
    public void testGetById() {
        doReturn(customerDebtEntity).when(customerDebtService).getById(anyLong());

        assertEquals(CustomerDebt.class, customerDebtRepositoryAdapter.getById(anyLong()).getClass());

        verify(customerDebtService, times(1)).getById(anyLong());
    }

    @Test
    public void testSaveOrUpdate() {
        CustomerDebt customerDebt = new CustomerDebt();
        customerDebt.setId(1L);
        customerDebt.setCustomerIdentifier("test");
        customerDebt.setName("test");
        customerDebt.setEmail("test");
        customerDebt.setAmount(new BigDecimal(100));
        customerDebt.setExpirationDate(new Date());

        doReturn(customerDebtEntity).when(customerDebtService).saveOrUpdate(any());

        assertEquals(CustomerDebt.class, customerDebtRepositoryAdapter.saveOrUpdate(customerDebt).getClass());

        verify(customerDebtService, times(1)).saveOrUpdate(any());
    }

    @Test
    public void testSaveOrUpdateCustomerDebtEntityNull() {
        CustomerDebt customerDebt = new CustomerDebt();
        customerDebt.setId(1L);
        customerDebt.setCustomerIdentifier("test");
        customerDebt.setName("test");
        customerDebt.setEmail("test");
        customerDebt.setAmount(new BigDecimal(100));
        customerDebt.setExpirationDate(new Date());

        doReturn(null).when(customerDebtService).saveOrUpdate(any());

        assertNull(customerDebtRepositoryAdapter.saveOrUpdate(customerDebt));

        verify(customerDebtService, times(1)).saveOrUpdate(any());
    }

    @Test
    public void testDelete() {
        doReturn(customerDebtEntity).when(customerDebtService).getById(anyLong());
        doReturn(true).when(customerDebtService).delete(anyLong());

        assertTrue(customerDebtRepositoryAdapter.delete(1L));

        verify(customerDebtService, times(1)).delete(anyLong());
    }

    @Test
    public void testDeleteFalse() {
        CustomerDebtEntity customerDebtExist = new CustomerDebtEntity();

        doReturn(customerDebtExist).when(customerDebtService).getById(anyLong());
        doReturn(false).when(customerDebtService).delete(anyLong());

        assertFalse(customerDebtRepositoryAdapter.delete(1L));

        verify(customerDebtService, times(1)).getById(anyLong());
        verify(customerDebtService, times(1)).delete(anyLong());
    }
}
