package satellite;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 сервис по добавлению спутников в группировку + исполенние работ всех миссий представляет собой
 паттерн FACADE который инкапсулирует ConstellationService и SatelliteService
 */

@Service
@RequiredArgsConstructor
public class SpaceOperationCenterService {
    private final ConstellationService constellationService;
    private final SatelliteServiceImpl satelliteService;

    @LogExecutionTime
    public void addSatellite(AddSatelliteRequest request){
        Satellite satellite = satelliteService.createSatellite(request.getSatelliteParam());
        constellationService.addSatelliteToConstellation(request.getConstellationName(), satellite);
    }

    @LogExecutionTime
    public void executeMission(MissionRequest request){
        constellationService.activateAllSatellite(request.getConstellationName());
        constellationService.executeConstellationMission(request.getConstellationName());
    }

    public void quicStart(String name, SatelliteParam param){
        constellationService.createAndSaveConstellation(name);
        addSatellite(new AddSatelliteRequest(name, param));
    }
}
