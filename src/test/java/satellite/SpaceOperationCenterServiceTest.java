package satellite;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpaceOperationCenterServiceTest {

    @Test
    @DisplayName("Сервис выполняет мисиию всей группировки")
    void executeAllMissionsTest(){
        ConstellationRepository repository = new ConstellationRepository();
        SatelliteConstellation constellation = new SatelliteConstellation("SatelliteG2");
        repository.addConstellation(constellation);
        SpaceOperationCenterService service = new SpaceOperationCenterService(repository);


        service.addSatelliteToConstellation(constellation.getConstellationName(), new ImagingSatelite("img1", 0.55, 2560));
        service.addSatelliteToConstellation(constellation.getConstellationName(), new CommunicationSatellite("com1", 0.55, 500));
        service.activateAllSatellite(constellation.getConstellationName());
        service.executeConstellationMission(constellation.getConstellationName());

        Assertions.assertNotNull(repository.getAllConstellation());

    }
}
