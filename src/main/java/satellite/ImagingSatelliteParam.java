package satellite;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ImagingSatelliteParam extends  SatelliteParam{
    private double resolution;

    public ImagingSatelliteParam(String name, double batteryLevel, double resolution){
        super(SatelliteType.IMAGE, name, batteryLevel);
        this.resolution = resolution;
    }
}
