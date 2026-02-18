package satellite;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GlobalSystemMockTest {

    @Mock
    private ConstellationRepository repository;

    @InjectMocks
    private SpaceOperationCenterService service;

    @Test
    @DisplayName("Проектный тест: Создание и инициализация всей системы")
    void testFullSystemInitialization() {
        String name = "Group1";
        service.createAndSaveConstellation("Group1");
        verify(repository, times(1))
                .addConstellation(any(SatelliteConstellation.class));
    }

    @Test
    @DisplayName("Проектный тест: Логика работы спутника через сервис")
    void testSatelliteMissionFlowViaService() {
        String groupName = "Group";
        SatelliteConstellation constellation = new SatelliteConstellation(groupName);
        ImagingSatelite spySat = spy(new ImagingSatelite("Camera-1", 0.8, 5.0));
        constellation.addSatellite(spySat);

        when(repository.getConstellation(groupName)).thenReturn(constellation);
        service.activateAllSatellite(groupName);
        service.executeConstellationMission(groupName);
        verify(spySat, atLeastOnce()).performMission();
        assertTrue(spySat.isActive());
        assertEquals(1, spySat.getPhotosTaken());
        verify(repository, atLeast(2)).getConstellation(groupName);
    }

    @Test
    @DisplayName("Проектный тест: Защита системы (обработка отсутствующих данных)")
    void testSystemResilience() {
        when(repository.getConstellation("Empty")).thenReturn(null);
        assertDoesNotThrow(() -> {
            service.executeConstellationMission("Empty");
        });
        verify(repository).getConstellation("Empty");
    }
}
