package satellite;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DomainClassesTest {

    @Test
    @DisplayName("Критический уровень зарада")
    void testEnergySystem() {
        EnergySystem energy = new EnergySystem(0.20);
        energy.consumeBattery(0.15);

        Assertions.assertEquals(0.05, energy.getBatteryLevel(), 0.001);
        Assertions.assertTrue(energy.isCriticleLevel());
        Assertions.assertNotNull(energy.toString());

    }

    @Test
    @DisplayName("Проверка состояний Satellite State")
    void testSatelliteState(){
        SatelliteState state = new SatelliteState();
        Assertions.assertFalse(state.isActive());
        state.activate();
        Assertions.assertTrue(state.isActive());
        state.deactivate();
        Assertions.assertFalse(state.isActive());
        Assertions.assertNotNull(state.toString());
        Assertions.assertNotNull(state.hashCode());
        Assertions.assertNotNull(state.getClass());
    }

    @Test
    @DisplayName("Satellite Проверка логики активации и деактивации")
    void testSatelliteBaseLogic() {
        CommunicationSatellite com = new CommunicationSatellite("Зенит", 0.45, 500);
        Assertions.assertTrue(com.activate());
        Assertions.assertTrue(com.isActive());

        CommunicationSatellite com2 = new CommunicationSatellite("Зенит22", 0.05, 500);
        Assertions.assertFalse(com2.activate());

        Assertions.assertNotNull(com.toString());
        Assertions.assertTrue(com.toString().contains("Зенит"));
    }

    @Test
    @DisplayName("SatelliteConstellation: Массовые операции")
    void testConstellationLogic() {
        SatelliteConstellation constellation = new SatelliteConstellation("Orion12");


        CommunicationSatellite sat1 = new CommunicationSatellite("Sat1", 50.0,100);
        ImagingSatelite sat2 = new ImagingSatelite("Sat2", 0.9, 1440);

        constellation.addSatellite(sat1);
        constellation.addSatellite(sat2);

        Assertions.assertEquals(2, constellation.getSatelites().size());
        Assertions.assertEquals("Orion12", constellation.getConstellationName());

        constellation.ActivateAllSatellites();
        Assertions.assertTrue(sat1.isActive());
        Assertions.assertTrue(sat2.isActive());
        constellation.executeAllMission();

        String details = constellation.getSatellitesDetailedMultiline();
        Assertions.assertNotNull(details);
        Assertions.assertTrue(details.contains("Sat1"));
    }

}
