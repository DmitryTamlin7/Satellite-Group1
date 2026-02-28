package satellite;


import org.springframework.stereotype.Component;

@Component
public class ImagingSatelliteFactory implements  SatelliteFactory{

        @Override
        public boolean isSatelliteTypeSupported(SatelliteType type){
            return type == SatelliteType.IMAGE;
        }

    @Override
    public Satellite createSatelliteWithParameter(SatelliteParam param) {
        if(!(param instanceof  ImagingSatelliteParam)){
            throw new SpaceOperationException("Неверный тип для спутника Зондирования");
        }

        ImagingSatelliteParam imgParam = (ImagingSatelliteParam) param;

        EnergySystem energy = EnergySystem.builder()
                .maxBattery(imgParam.getBatteryLevel())
                .batteryLevel(imgParam.getBatteryLevel())
                .minBattery(0)
                .criticleLevel(imgParam.getBatteryLevel() * 0.2)
                .build();

        return new ImagingSatelite(imgParam.getName(), imgParam.getResolution(), energy);
    }
}

