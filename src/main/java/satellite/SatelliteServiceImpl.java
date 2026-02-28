package satellite;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SatelliteServiceImpl implements  SatelliteService{

    private final List<SatelliteFactory> factories;

    public SatelliteServiceImpl(List<SatelliteFactory> factories) {
        this.factories = factories;
    }

    @Override
    public Satellite createSatellite(SatelliteParam param) {
        for (SatelliteFactory factory : factories){
            if (factory.isSatelliteTypeSupported(param.getType())){
                return factory.createSatelliteWithParameter(param);
            }
        }
        throw new SpaceOperationException("Фабрика типа " + param.getType() + " Не найдена");

    }
}
