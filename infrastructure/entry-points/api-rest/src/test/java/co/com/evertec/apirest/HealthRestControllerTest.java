package co.com.evertec.apirest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class HealthRestControllerTest {
    @InjectMocks
    HealthRestController healthRestController;

    @Test
    public void testHealth() {
        assertEquals(HttpStatus.OK, healthRestController.health().getStatusCode());
    }
}
