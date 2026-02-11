package satelite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("ЗАПСК СИСТЕМЫ УПРАЛЕНИЯ СПУТНИКОВОЙ ГРУППИРОВКОЙ");
        System.out.println("\nСоздание спутниковой группировки:\n");

        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        ConstellationRepository constellationRepository = context.getBean(ConstellationRepository.class);
        SpaceOperationCenterService operationCenter = context.getBean(SpaceOperationCenterService.class);

        CommunicationSatellite comsat1 = new CommunicationSatellite(
                "Спутник связи Рассвет-1",
                0.85,
                500.0
        );

        CommunicationSatellite comsat2 = new CommunicationSatellite(
                "Спутник связи Рассвет-2",
                0.75,
                1000.0
        );

        ImagingSatelite imgsat1 = new ImagingSatelite(
                "Спутник зондирования Зенит-1 ",
                0.99,
                2880
        );

        ImagingSatelite imgsat2 = new ImagingSatelite(
                "Спутник зондирования Зенит-2 ",
                0.44,
                 4350
        );

        ImagingSatelite imgsat3 = new ImagingSatelite(
                "Спутник зондирования Зенит-3 ",
                0.12,
                1080
        );

        operationCenter.createAndSaveConstellation("Орбита 1");
        operationCenter.createAndSaveConstellation("Орбита 2");

        System.out.println("\n🛰️Добавление спутников: \n");
        operationCenter.addSatelliteToConstellation("Орбита 1", comsat1);
        operationCenter.addSatelliteToConstellation("Орбита 2", comsat2);
        operationCenter.addSatelliteToConstellation("Орбита 1", imgsat1);
        operationCenter.addSatelliteToConstellation("Орбита 1", imgsat2);
        operationCenter.addSatelliteToConstellation("Орбита 2", imgsat3);


        System.out.println("\nПодробная информация о спутниках группировки\n");
        operationCenter.showConstellationStatus("Орбита 1");
        operationCenter.showConstellationStatus("Орбита 2");


        System.out.println("\n===АКТИВАЦИЯ СПУТНИКОВ ГРУППИРОВКИ===\n");
        operationCenter.activateAllSatellite("Орбита 1");
        operationCenter.activateAllSatellite("Орбита 2");

        operationCenter.executeConstellationMission("Орбита 1");
        operationCenter.executeConstellationMission("Орбита 2");

        System.out.println("\nПодробная информация о спутниках группировки\n");
        operationCenter.showConstellationStatus("Орбита 1");
        operationCenter.showConstellationStatus("Орбита 2");

        System.out.println(constellationRepository.getAllConstellation().toString());



    }
}