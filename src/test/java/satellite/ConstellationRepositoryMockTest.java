package satellite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConstellationRepositoryMockTest {

    @Mock
    private ConstellationRepository repositoryMock;

    @InjectMocks
    private ConstellationService service;

    @Test
    @DisplayName("Вызов метода при добавлении группировки в репозиторий")
    void testServiceAddsConstellation(){
        service.createAndSaveConstellation("ЮрийГагарин7");
        verify(repositoryMock, times(1))
                .addConstellation(any(SatelliteConstellation.class));
    }

    @Test
    @DisplayName("Возврат заготовленной группировки из Mock-объекта")
    void testMockReturnValues() {
        String name = "Space1";
        when(repositoryMock.containsConstellation(name)).thenReturn(true);
        boolean exist = repositoryMock.containsConstellation(name);
        assertTrue(exist);
        verify(repositoryMock).containsConstellation(name);
    }
}