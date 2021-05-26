package co.com.evertec.usecase.impl;

import co.com.evertec.model.CustomerDebt;
import co.com.evertec.model.gateway.CustomerDebtGateway;
import co.com.evertec.usecase.CustomerDebtUseCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CustomerDebtUseCaseTest {

    @InjectMocks
    CustomerDebtUseCase customerDebtUseCase;

    @Mock
    CustomerDebtGateway customerDebtGateway;

    @Mock
    MessageSource messageSource;

    @Test
    public void testGetAll() {
        doReturn(new ArrayList<>()).when(customerDebtGateway).getAll();
        doReturn("").when(messageSource).getMessage(anyString(), any(), any());

        assertEquals(HashMap.class, customerDebtUseCase.getAll().getClass());

        verify(customerDebtGateway, times(1)).getAll();
        verify(messageSource, times(1)).getMessage(anyString(), any(), any());
    }

    @Test
    public void testGetById() {
        doReturn(new CustomerDebt()).when(customerDebtGateway).getById(anyLong());
        doReturn("").when(messageSource).getMessage(anyString(), any(), any());

        assertEquals(HashMap.class, customerDebtUseCase.getById(anyLong()).getClass());

        verify(customerDebtGateway, times(1)).getById(anyLong());
        verify(messageSource, times(1)).getMessage(anyString(), any(), any());
    }

    @Test
    public void testSave() {
        doReturn(new CustomerDebt()).when(customerDebtGateway).saveOrUpdate(any());
        doReturn("").when(messageSource).getMessage(anyString(), any(), any());
        assertEquals(HashMap.class, customerDebtUseCase.saveOrUpdate(new CustomerDebt(), false).getClass());
    }

    @Test
    public void testUpdate() {
        CustomerDebt customerDebt = new CustomerDebt(1L, "test", "test", "test", new BigDecimal(0), "test", new Date());
        doReturn(customerDebt).when(customerDebtGateway).getById(anyLong());
        doReturn(customerDebt).when(customerDebtGateway).saveOrUpdate(any());
        doReturn("").when(messageSource).getMessage(anyString(), any(), any());
        assertEquals(HashMap.class, customerDebtUseCase.saveOrUpdate(customerDebt, true).getClass());
    }

    @Test
    public void testUpdateNotExist() {
        doReturn(new CustomerDebt()).when(customerDebtGateway).getById(anyLong());
        doReturn("").when(messageSource).getMessage(anyString(), any(), any());
        assertEquals(HashMap.class, customerDebtUseCase.saveOrUpdate(new CustomerDebt(), true).getClass());
    }

    @Test
    public void testDelete() {
        doReturn(true).when(customerDebtGateway).delete(anyLong());
        doReturn("").when(messageSource).getMessage(anyString(), any(), any());

        assertEquals(HashMap.class, customerDebtUseCase.delete(1L).getClass());

        verify(customerDebtGateway, times(1)).delete(anyLong());
        verify(messageSource, times(1)).getMessage(anyString(), any(), any());
    }
}
