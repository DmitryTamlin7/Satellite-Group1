package satellite;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnergySystem {
    private double batteryLevel;
    private double maxBattery;
    private double minBattery;
    private double criticleLevel;

    public boolean consumeBattery(double amount) {
        if (!isCriticleLevel()) {
            batteryLevel -= amount;
            if (batteryLevel < minBattery) batteryLevel = minBattery;
            return true;
        }
        return false;
    }

    public void rechargeBattery(double amount) {
        if (batteryLevel + amount <= maxBattery) {
            batteryLevel += amount;
        } else {
            batteryLevel = maxBattery;
        }
    }

    public boolean isCriticleLevel() {
        return batteryLevel <= criticleLevel;
    }
}