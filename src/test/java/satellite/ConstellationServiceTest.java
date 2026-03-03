package satellite;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConstellationServiceTest {

    private EnergySystem createEnergy() {
        return EnergySystem.builder().batteryLevel(0.9).maxBattery(1.0).build();
    }

    @Test
    @DisplayName("Сервис выполняет миссию всей группировки")
    void executeAllMissionsTest(){
        ConstellationRepository repository = new ConstellationRepository();
        String groupName = "SatelliteG2";
        SatelliteConstellation constellation = new SatelliteConstellation(groupName);
        repository.addConstellation(constellation);

        ConstellationService service = new ConstellationService(repository);
        service.addSatelliteToConstellation(groupName, new ImagingSatelite("img1", 2560.0, createEnergy()));
        service.addSatelliteToConstellation(groupName, new CommunicationSatellite("com1", 500.0, createEnergy()));

        service.activateAllSatellite(groupName);
        service.executeConstellationMission(groupName);

        Assertions.assertEquals(2, repository.getConstellation(groupName).getSatelites().size());
    }
}