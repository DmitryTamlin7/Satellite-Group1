package satellite;

public class ImagingSatelliteFactory implements  SatelliteFactory{
    @Override
    public Satellite createSatellite(String name, double maxEnergy, double resolution){
        EnergySystem energy = new EnergySystem.Builder()
                .maxLevel(maxEnergy)
                .batteryLevel(maxEnergy)
                .minLevel(0)
                .criticleLevel(maxEnergy * 0.2)
                .build();
        return new ImagingSatelite(name, resolution, energy);
    }
}
