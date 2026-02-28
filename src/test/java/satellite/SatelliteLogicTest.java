package satellite;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SatelliteLogicTest {
    private EnergySystem createEnergy(double level) {
        return EnergySystem.builder()
                .batteryLevel(level)
                .maxBattery(1.0)
                .minBattery(0.0)
                .criticleLevel(0.20)
                .build();
    }

    @Test
    @DisplayName("Низкий заряд спутника: не должен быть активен")
    void shouldNotActivateWithLowBattery() {
        CommunicationSatellite sat = new CommunicationSatellite("Action", 10.0, createEnergy(0.05));
        Assertions.assertFalse(sat.isActive());
        Assertions.assertFalse(sat.activate());
    }

    @Test
    @DisplayName("Спутник связи должен успешно выполнять миссию")
    void commSatMissionTest() {
        double initialLevel = 0.77;
        CommunicationSatellite sat = new CommunicationSatellite("Test", 100.0, createEnergy(initialLevel));

        ConstellationRepository repository = new ConstellationRepository();
        SatelliteConstellation constellation = new SatelliteConstellation("Орбита1");
        repository.addConstellation(constellation);
        SpaceOperationCenterService service = new SpaceOperationCenterService(repository);

        service.addSatelliteToConstellation("Орбита1", sat);
        service.activateAllSatellite("Орбита1");
        service.executeConstellationMission("Орбита1");
        Assertions.assertTrue(sat.getEnergy().getBatteryLevel() < initialLevel);
    }

    @Test
    @DisplayName("Спутник съемки должен считать количество фото")
    void imagingSatMissionTest() {
        ImagingSatelite img = new ImagingSatelite("Фото", 1080.0, createEnergy(0.88));

        ConstellationRepository repository = new ConstellationRepository();
        SatelliteConstellation constellation = new SatelliteConstellation("Орбита2");
        repository.addConstellation(constellation);
        SpaceOperationCenterService service = new SpaceOperationCenterService(repository);

        service.addSatelliteToConstellation("Орбита2", img);
        service.activateAllSatellite("Орбита2");

        service.executeConstellationMission("Орбита2");
        service.executeConstellationMission("Орбита2");

        Assertions.assertEquals(2, img.getPhotosTaken());
    }

    @Test
    @DisplayName("Покрытие toString и базовых методов")
    void coverageBooster() {
        CommunicationSatellite com = new CommunicationSatellite("Зенит", 500.0, createEnergy(0.99));

        Assertions.assertNotNull(com.toString());
        Assertions.assertNotNull(com.getName());
        Assertions.assertNotNull(com.getEnergy());
        Assertions.assertTrue(com.toString().contains("Зенит"));
    }
}