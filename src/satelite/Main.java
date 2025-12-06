package satelite;

public class Main {
    public static void main(String[] args) {
        System.out.println("ЗАПСК СИСТЕМЫ УПРАЛЕНИЯ СПУТНИКОВОЙ ГРУППИРОВКОЙ");
        System.out.println("\nСоздание спутниковой группировки:\n");

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



        System.out.println("Создан спутник: " + comsat1.toString());
        System.out.println("Создан спутник: " + comsat2.toString());
        System.out.println("Создан спутник: " + imgsat1.toString());
        System.out.println("Создан спутник: " + imgsat2.toString());
        System.out.println("Создан спутник: " + imgsat3.toString());




        System.out.println("\nФормирование группировки\n");
        SatelliteConstellation constellation = new SatelliteConstellation("RU-BASIK");
        System.out.printf("Создана спутниковая группировка %s: \n", constellation.getConstellationName());
        constellation.addSatelite(comsat1);
        constellation.addSatelite(comsat2);
        constellation.addSatelite(imgsat1);
        constellation.addSatelite(imgsat2);
        constellation.addSatelite(imgsat3);

        System.out.println("\nПодробная информация о спутниках группировки\n");
        System.out.println(constellation.getSatellitesDetailedMultiline());


        constellation.ActivateAllSatelites();
        constellation.executeAllMission();

        System.out.println("\nПодробная информация о спутниках группировки\n");
        System.out.println(constellation.getSatellitesDetailedMultiline());

    }
}