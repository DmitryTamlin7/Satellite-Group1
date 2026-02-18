package satellite;

public interface SatelliteFactory {
    Satellite createSatellite(String name, double maxEnergy, double specificParam);
}
