package satellite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import satelite.ConstellationRepository;
import satelite.SatelliteConstellation;
import satelite.SpaceOperationCenterService;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ConstellationRepositoryMockTest {

    @Mock
    private ConstellationRepository repositoryMock;

    @InjectMocks
    private SpaceOperationCenterService service;

    @Test
    @DisplayName("Вызов метода при добваления в репозиторий")
    void testServiceAddsConstellation(){
        service.createAndSaveConstellation("ЮрийГагарин7");
        verify(repositoryMock, times(1))
                .addConstellation(any(SatelliteConstellation.class));
    }

    @Test
    @DisplayName("Возврат заготовленной группировки")
    void testMockReturnValues() {
        String name = "Space1";
        SatelliteConstellation con = new SatelliteConstellation(name);

        when(repositoryMock.containsConstellation(name)).thenReturn(true);
        boolean exist = repositoryMock.containsConstellation(name);
        assertTrue(exist);
        verify(repositoryMock).containsConstellation(name);
    }

}

