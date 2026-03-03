package satellite;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddSatelliteRequest {
    private String constellationName;
    private SatelliteParam satelliteParam;
}
