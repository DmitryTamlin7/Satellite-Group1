package satellite;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Main.class)
public class ConstellationRepositoryIntegrationTest {

    @Autowired
    private SpaceOperationCenterService service;

    @Autowired
    private ConstellationRepository repository;

    @Test
    @DisplayName("Полный тест жизненный цикл группировки")
    void fullTest(){
        String name = "Kalinka";

        service.createAndSaveConstellation(name);
        CommunicationSatellite com = new CommunicationSatellite("Пушкин", 0.55, 50.0);
        service.addSatelliteToConstellation("Kalinka", com);

        service.activateAllSatellite("Kalinka");

        SatelliteConstellation result = repository.getConstellation(name);
        Assertions.assertEquals(1, result.getSatelites().size());
        Assertions.assertTrue(result.getSatelites().get(0).getState().isActive());
        service.executeConstellationMission(name);

        System.out.println("Интеграционный тест пройден успешно!");

    }

}
