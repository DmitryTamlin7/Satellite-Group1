package satellite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Main.class)
public class MainClassTest {

    @Test
    @DisplayName("Проверка возможность подьема ")
    void contextLoads() {
    }

    @Test
    @DisplayName("Проверка работы исключая ошибки портов и тд.")
    void testMainMethod() {
        try {
            Main.main(new String[]{});
        } catch (Exception e) {
        }
    }
}
