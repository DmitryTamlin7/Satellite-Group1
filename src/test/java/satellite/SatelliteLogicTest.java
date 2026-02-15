package satellite;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SatelliteLogicTest {

    @Test
    @DisplayName("Низкий заряд спутника")
    void shouldNotActivateWithLowBattery() {
        double energy = 0.05;
        CommunicationSatellite sat = new CommunicationSatellite("Action",  energy, 10);
        boolean res = sat.isActive();
        Assertions.assertFalse(res);
        Assertions.assertFalse(sat.getState().isActive());
    }

    @Test
    @DisplayName("Спутник связи должен успешно выполнять миссию")
    void commSatMissionTest() {
        CommunicationSatellite sat = new CommunicationSatellite("Test", 0.77, 100);

        ConstellationRepository repository = new ConstellationRepository();
        SatelliteConstellation constellation = new SatelliteConstellation("Орбита1");
        repository.addConstellation(constellation);
        SpaceOperationCenterService service = new SpaceOperationCenterService(repository);

        service.addSatelliteToConstellation("Орбита1",sat);
        service.activateAllSatellite("Орбита1");
        service.executeConstellationMission("Орбита1");

        Assertions.assertTrue(sat.getBatteryLevel() < 0.76);
    }

    @Test
    @DisplayName("Спутник съемки должен считать количество фото")
    void imagingSatMissionTest() {
        ImagingSatelite img = new ImagingSatelite("Фото", 0.88, 1080);
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
        CommunicationSatellite com = new CommunicationSatellite("Зенит", 0.99, 500);
        String info = com.toString();
        String details = com.getBaseDetails();
        Assertions.assertNotNull(com.toString());
        Assertions.assertNotNull(com.getName());
        Assertions.assertNotNull(com.getEnergy());
        Assertions.assertNotNull(info);
        Assertions.assertTrue(info.contains("Зенит"));
        Assertions.assertNotNull(details);
    }

    @Test
    @DisplayName("Невозможность активации при низком заряде")
    void testCannotActivateOnLowBattery() {
        CommunicationSatellite sat = new CommunicationSatellite("Dead-Sat", 0.05, 500);
        boolean result = sat.activate();
        Assertions.assertFalse(result, "Метод activate() должен вернуть false");
        Assertions.assertFalse(sat.isActive(), "Состояние не должно измениться на active");
    }






}
