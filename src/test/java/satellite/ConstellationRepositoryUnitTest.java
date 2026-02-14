package satellite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import satelite.ConstellationRepository;
import satelite.SatelliteConstellation;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConstellationRepositoryUnitTest {

    private final ConstellationRepository repository = new ConstellationRepository();

    @Test
    @DisplayName("Сохранение группировки")
    void shouldSaveConstellation() {
        String name = "Орбита-22";
        SatelliteConstellation constellation = new SatelliteConstellation(name);
        repository.addConstellation(constellation);

        Map<String, SatelliteConstellation> group = repository.getAllConstellation();
        Assertions.assertEquals(1, group.size(), "добавлен 1 спуник в группировку");
        Assertions.assertTrue(group.containsKey(name), "Ключ имя группировки");
        Assertions.assertEquals(constellation, group.get(name), "Обьект один и тот же должен совпадать");
    }

    @Test
    @DisplayName("Поиск несуществующей группировки вернет Optional")
    void shouldReturnEmptyWhenConstellationNotFound(){
        assertThrows(RuntimeException.class, () -> {
            repository.getConstellation("Non-Existing");
        }, "Должна быть ошибка RuntimeException, если группировка не найдена");
    }

    @Test
    @DisplayName("Ошибка при получении несуществующей группы")
    void testError() {
        assertThrows(RuntimeException.class, () -> repository.getConstellation("NullOrbit"));
    }


}