package satelite;


import org.springframework.stereotype.Service;

@Service
public class SpaceOperationCenterService {
    private final ConstellationRepository repository;

    public SpaceOperationCenterService(ConstellationRepository repository) {
        this.repository = repository;
    }

    public void createAndSaveConstellation(String name){
        SatelliteConstellation constellation = new SatelliteConstellation(name);
        repository.addConstellation(constellation);
    }

    public void addSatelliteToConstellation(String constellationName, Satellite satelite) {
        SatelliteConstellation constellation = repository.getConstellation(constellationName);
        constellation.addSatelite(satelite);
        System.out.printf("Добавлен спутник %s в Группировку %s \n",
                satelite.getName(),
                constellation.getConstellationName());
    }

    public void executeConstellationMission(String constellationName){
        SatelliteConstellation constellation = repository.getConstellation(constellationName);
        System.out.println("\n=== ВЫПОЛНЕНИЕ МИССИЙ ДЛЯ ГРУППИРОВКИ: " + constellationName + " ===") ;
        constellation.executeAllMission();
    }

    public void activateAllSatellite(String constellationName){
        SatelliteConstellation constellation = repository.getConstellation(constellationName);
        System.out.printf("\nАктивация Спутников в Группировке %s ", constellation.getConstellationName());
        for (Satellite satelite : constellation.getSatelites()){
            satelite.activate();
        }
    }

    public void showConstellationStatus(String constellationName){
        SatelliteConstellation constellation = repository.getConstellation(constellationName);
        System.out.printf("\n===СТАТУС ГРУППИРОВКИ: %s ====\n", constellationName);
        System.out.printf("Колличество спутников: %s\n", constellation.getSatelites().size());
        for (Satellite satelite : constellation.getSatelites()){
            System.out.println(satelite.getBaseDetails());
        }
    }




}
