package satellite;

import org.springframework.stereotype.Component;

@Component
public class CommunicationSatelliteFactory implements  SatelliteFactory{

    @Override
    public boolean isSatelliteTypeSupported(SatelliteType type){
        return type == SatelliteType.COMMUNICATION;
    }


    @Override
    public Satellite createSatelliteWithParameter(SatelliteParam param){
        if (!(param instanceof CommunicationSatelliteParam)){
            throw new SpaceOperationException("Неверный тип для спутника связи");
        }

        CommunicationSatelliteParam commParam = (CommunicationSatelliteParam)  param;

        EnergySystem energy = EnergySystem.builder()
                .maxBattery(commParam.getBatteryLevel())
                .batteryLevel(commParam.getBatteryLevel())
                .minBattery(0)
                .criticleLevel(commParam.getBatteryLevel() * 0.2)
                .build();

        return new CommunicationSatellite(commParam.getName(), commParam.getBandWidth(), energy);
    }

}
