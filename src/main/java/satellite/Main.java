package satellite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("ЗАПУСК СИСТЕМЫ УПРАВЛЕНИЯ СПУТНИКОВОЙ ГРУППИРОВКОЙ");

        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        ConstellationRepository constellationRepository = context.getBean(ConstellationRepository.class);
        ConstellationService operationCenter = context.getBean(ConstellationService.class);
        SatelliteService satelliteService = context.getBean(SatelliteService.class);

        System.out.println("\n--- Создание спутников через SatelliteService ---");


        Satellite comsat1 = satelliteService.createSatellite(
                new CommunicationSatelliteParam("Спутник связи Рассвет-1", 0.99, 500.0)
        );

        Satellite comsat2 = satelliteService.createSatellite(
                new CommunicationSatelliteParam("Спутник связи Рассвет-2", 0.75, 1000.0)
        );

        Satellite imgsat1 = satelliteService.createSatellite(
                new ImagingSatelliteParam("Спутник зондирования Зенит-1", 0.99, 2880.0)
        );

        Satellite imgsat2 = satelliteService.createSatellite(
                new ImagingSatelliteParam("Спутник зондирования Зенит-2", 0.44, 4350.0)
        );

        Satellite imgsat3 = satelliteService.createSatellite(
                new ImagingSatelliteParam("Спутник зондирования Зенит-3", 0.12, 1080.0)
        );

        operationCenter.createAndSaveConstellation("Орбита 1");
        operationCenter.createAndSaveConstellation("Орбита 2");

        System.out.println("\n🛰️ Добавление спутников в группировки: \n");
        operationCenter.addSatelliteToConstellation("Орбита 1", comsat1);
        operationCenter.addSatelliteToConstellation("Орбита 2", comsat2);
        operationCenter.addSatelliteToConstellation("Орбита 1", imgsat1);
        operationCenter.addSatelliteToConstellation("Орбита 1", imgsat2);
        operationCenter.addSatelliteToConstellation("Орбита 2", imgsat3);

        System.out.println("\n--- Статус группировок до активации ---");
        operationCenter.showConstellationStatus("Орбита 1");
        operationCenter.showConstellationStatus("Орбита 2");

        System.out.println("\n=== АКТИВАЦИЯ И ВЫПОЛНЕНИЕ МИССИЙ ===\n");
        operationCenter.activateAllSatellite("Орбита 1");
        operationCenter.activateAllSatellite("Орбита 2");

        operationCenter.executeConstellationMission("Орбита 1");
        operationCenter.executeConstellationMission("Орбита 2");

        System.out.println("\n--- Статус группировок после миссий ---");
        operationCenter.showConstellationStatus("Орбита 1");
        operationCenter.showConstellationStatus("Орбита 2");

        System.out.println("\nФинальный отчет репозитория:");
        System.out.println(constellationRepository.getAllConstellation().toString());
    }
}