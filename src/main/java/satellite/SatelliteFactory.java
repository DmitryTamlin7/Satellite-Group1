package satellite;

public interface SatelliteFactory {
    Satellite createSatelliteWithParameter(SatelliteParam param);
    boolean isSatelliteTypeSupported(SatelliteType type);
}
