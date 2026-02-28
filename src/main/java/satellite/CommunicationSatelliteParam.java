package satellite;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommunicationSatelliteParam extends SatelliteParam{
    private double bandWidth;

    public CommunicationSatelliteParam(String name, double batteryLevel, double bandWidth){
        super(SatelliteType.COMMUNICATION, name, batteryLevel);
        this.bandWidth = bandWidth;
    }

}
