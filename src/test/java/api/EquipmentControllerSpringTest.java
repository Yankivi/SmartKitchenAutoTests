package api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EquipmentControllerSpringTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllEquipments() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/equipments/all", String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).contains("Fork");
    }

    @Test
    public void testGetEquipmentById() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/equipments/1", String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).contains("Fork");
    }
}
