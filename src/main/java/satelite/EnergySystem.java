package satelite;

import lombok.Data;

@Data
public class EnergySystem {
    private double batteryLevel;
    private final double criticleLevel = 0.20;

    public EnergySystem(double batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public void consumeBattery(double amount){
        batteryLevel -= amount;
        if (batteryLevel < 0){
            batteryLevel = 0;
        }
    }


    public boolean isCriticleLevel(){
        return  batteryLevel <= criticleLevel;
    }

}
