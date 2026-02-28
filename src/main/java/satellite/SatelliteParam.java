package satellite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public abstract class SatelliteParam {
    private SatelliteType type;
    String name;
    double batteryLevel;
}
