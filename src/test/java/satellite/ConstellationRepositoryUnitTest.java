package satellite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

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

        Map<String, SatelliteConstellation> allGroups = repository.getAllConstellation();

        Assertions.assertEquals(1, allGroups.size());
        Assertions.assertTrue(allGroups.containsKey(name));
        Assertions.assertEquals(constellation, allGroups.get(name));
    }

    @Test
    @DisplayName("Ошибка при поиске несуществующей группировки")
    void shouldThrowExceptionWhenNotFound(){
        assertThrows(RuntimeException.class, () -> {
            repository.getConstellation("Non-Existing");
        });
    }

    @Test
    @DisplayName("Проверка, что пустой репозиторий возвращает пустую карту, а не null")
    void testEmptyRepository() {
        Assertions.assertNotNull(repository.getAllConstellation());
        Assertions.assertTrue(repository.getAllConstellation().isEmpty());
    }
}