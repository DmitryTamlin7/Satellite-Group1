package satelite;

import java.util.ArrayList;
import java.util.List;

public class SatelliteConstellation {
    private String constellationName;
    private List<Satelite> satelites;

    public SatelliteConstellation(String constellationName) {
        this.constellationName = constellationName;
        this.satelites = new ArrayList<>();
    }

    public void addSatelite(Satelite satelite){
        satelites.add(satelite);
        System.out.printf("%s добавлен с группировку '%s' \n",
                satelite.getName(), constellationName);
    }


    public void executeAllMission() {
        System.out.printf("\nВыполенение миссий Группировки %s\n",
                constellationName.toUpperCase());
        for (Satelite s : satelites){
            s.performMission();
        }
    }

    public void ActivateAllSatelites(){
        System.out.printf("\nАктивация спутников\n");
        for (Satelite s : satelites){
            boolean activaited = s.activate();
            if (activaited){
                System.out.printf("✅ %s: Активация успешна\n", s.getName());
            }else {
                System.out.printf("❌ %s; Ошибка Активации (заряд: %d%%)\n", s.getName(), (int) (s.getBatteryLevel() * 100));
            }
        }

    }

    public List<Satelite> getSatelites() {
        return satelites;
    }

    public String getConstellationName() {
        return constellationName;
    }
}
