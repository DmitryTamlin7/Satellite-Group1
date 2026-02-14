package satellite;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import satelite.CommunicationSatellite;
import satelite.EnergySystem;

public class SatelliteLogicTest {

    @Test
    @DisplayName("Низкий зараяд спутника")
    void shouldNotActivateWithLowBattery() {
        double energy = 0.05;
        CommunicationSatellite sat = new CommunicationSatellite("Action",  energy, 10);
        boolean res = sat.isActive();
        Assertions.assertFalse(res);
        Assertions.assertFalse(sat.getState().isActive());
    }
}
