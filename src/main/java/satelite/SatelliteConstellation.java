package satelite;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
public class SatelliteConstellation {
    private String constellationName;
    private List<Satellite> satelites;

    public SatelliteConstellation(String constellationName) {
        this.constellationName = constellationName;
        this.satelites = new ArrayList<>();
    }

    public void addSatellite(Satellite satelite){
        satelites.add(satelite);
    }

    public void executeAllMission() {
        System.out.printf("\nВыполенение миссий Группировки %s\n",
                constellationName.toUpperCase());
        for (Satellite s : satelites){
            s.performMission();
        }
    }

    public void ActivateAllSatellites(){
        System.out.printf("\nАктивация спутников\n");
        for (Satellite s : satelites){
            boolean activaited = s.activate();
            if (activaited){
                System.out.printf("✅ %s: Активация успешна\n", s.getName());
            }else {
                System.out.printf("❌ %s; Ошибка Активации (заряд: %d%%)\n", s.getName(), (int) (s.getBatteryLevel() * 100));
            }
        }
    }

    public String getSatellitesDetailedMultiline() {
        return satelites.stream()
                .map(Satellite::getBaseDetails)
                .collect(Collectors.joining(",\n  ", "\n  ", "\n"));
    }

    public List<Satellite> getSatelites() {
        return satelites;
    }

    public String getConstellationName() {
        return constellationName;
    }
}
