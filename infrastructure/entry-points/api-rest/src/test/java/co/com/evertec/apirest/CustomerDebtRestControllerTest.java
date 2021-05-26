package co.com.evertec.apirest;

import co.com.evertec.model.CustomerDebt;
import co.com.evertec.usecase.CustomerDebtUseCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CustomerDebtRestControllerTest {

    @InjectMocks
    CustomerDebtRestController customerDebtRestController;

    @Mock
    CustomerDebtUseCase customerDebtUseCase;

    @Test
    public void testGetAll() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", 1);
        response.put("code", "MAP");
        response.put("message", "Test");
        response.put("data", new Object());

        doReturn(response).when(customerDebtUseCase).getAll();
        assertEquals(HttpStatus.OK, customerDebtRestController.getAll().getStatusCode());
        verify(customerDebtUseCase, times(1)).getAll();
    }

    @Test
    public void testGetAllNoContent() {
        doReturn(new HashMap<>()).when(customerDebtUseCase).getAll();
        assertEquals(HttpStatus.NO_CONTENT, customerDebtRestController.getAll().getStatusCode());
        verify(customerDebtUseCase, times(1)).getAll();
    }

    @Test
    public void testGetById() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", 1);
        response.put("code", "MAP");
        response.put("message", "Test");
        response.put("data", new Object());

        doReturn(response).when(customerDebtUseCase).getById(anyLong());
        assertEquals(HttpStatus.OK, customerDebtRestController.getById(anyLong()).getStatusCode());
        verify(customerDebtUseCase, times(1)).getById(anyLong());
    }

    @Test
    public void testGetByIdNoContent() {
        doReturn(new HashMap<>()).when(customerDebtUseCase).getById(anyLong());
        assertEquals(HttpStatus.NO_CONTENT, customerDebtRestController.getById(anyLong()).getStatusCode());
        verify(customerDebtUseCase, times(1)).getById(anyLong());
    }

    @Test
    public void testSave() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", 1);
        response.put("code", "MAP");
        response.put("message", "Test");
        response.put("data", new Object());

        doReturn(response).when(customerDebtUseCase).saveOrUpdate(any(), eq(false));
        assertEquals(HttpStatus.CREATED, customerDebtRestController.save(new CustomerDebt()).getStatusCode());
        verify(customerDebtUseCase, times(1)).saveOrUpdate(any(), eq(false));
    }

    @Test
    public void testSaveBadRequest() {
        doReturn(new HashMap<>()).when(customerDebtUseCase).saveOrUpdate(any(), eq(false));
        assertEquals(HttpStatus.BAD_REQUEST, customerDebtRestController.save(new CustomerDebt()).getStatusCode());
        verify(customerDebtUseCase, times(1)).saveOrUpdate(any(), eq(false));
    }

    @Test
    public void testUpdate() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", 1);
        response.put("code", "MAP");
        response.put("message", "Test");
        response.put("data", new Object());

        doReturn(response).when(customerDebtUseCase).saveOrUpdate(any(), eq(true));
        assertEquals(HttpStatus.CREATED, customerDebtRestController.update(new CustomerDebt()).getStatusCode());
        verify(customerDebtUseCase, times(1)).saveOrUpdate(any(), eq(true));
    }

    @Test
    public void testUpdateBadRequest() {
        doReturn(new HashMap<>()).when(customerDebtUseCase).saveOrUpdate(any(), eq(true));
        assertEquals(HttpStatus.BAD_REQUEST, customerDebtRestController.update(new CustomerDebt()).getStatusCode());
        verify(customerDebtUseCase, times(1)).saveOrUpdate(any(), eq(true));
    }

    @Test
    public void testDelete() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", 1);
        response.put("code", "MAP");
        response.put("message", "Test");
        response.put("data", new Object());

        doReturn(response).when(customerDebtUseCase).delete(anyLong());
        assertEquals(HttpStatus.OK, customerDebtRestController.delete(anyLong()).getStatusCode());
        verify(customerDebtUseCase, times(1)).delete(anyLong());
    }

    @Test
    public void testDeleteBadRequest() {
        doReturn(new HashMap<>()).when(customerDebtUseCase).delete(anyLong());
        assertEquals(HttpStatus.BAD_REQUEST, customerDebtRestController.delete(anyLong()).getStatusCode());
        verify(customerDebtUseCase, times(1)).delete(anyLong());
    }
}
