package satellite;

public class CommunicationSatelliteFactory implements  SatelliteFactory{
    @Override
    public Satellite createSatellite(String name, double maxEnergy, double bandwidth){
        EnergySystem energy = new EnergySystem.Builder()
                .maxLevel(maxEnergy)
                .batteryLevel(maxEnergy)
                .minLevel(0)
                .criticleLevel(maxEnergy * 0.2)
                .build();
        return new CommunicationSatellite(name, bandwidth, energy);
    }
}
