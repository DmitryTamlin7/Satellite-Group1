package satellite;

import lombok.Data;

@Data
public class EnergySystem {
    private double batteryLevel;
    private double maxBattery;
    private double minBattery;
    private double criticleLevel;

    private EnergySystem(Builder builder){
        this.batteryLevel = builder.batteryLevel;
        this.maxBattery = builder.maxBattery;
        this.minBattery = builder.minBattery;
        this.criticleLevel = builder.criticleLevel;
    }

    public boolean consumeBattery(double amount){
        if (!isCriticleLevel()){
            batteryLevel -= amount;
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

    public boolean isCriticleLevel(){
        return batteryLevel <= minBattery;
    }

    @Override
    public String toString() {
        return "EnergySystem{level=" + batteryLevel + "/" + maxBattery + "}";
    }


    public static class Builder{
        private double batteryLevel;
        private double maxBattery;
        private double minBattery = 0;
        private double criticleLevel = 0.20;

        public static Builder builder() {
            return new Builder();
        }

        public Builder batteryLevel(double batteryLevel) {
            this.batteryLevel = batteryLevel;
            return this;
        }

        public Builder maxLevel(double maxBattery){
            this.maxBattery = maxBattery;
            return this;
        }

        public Builder minLevel(double minBattery){
            this.minBattery =minBattery;
            return  this;
        }

        public Builder criticleLevel(double criticleLevel){
            this.criticleLevel = criticleLevel;
            return this;
        }

        public EnergySystem build(){
            if (maxBattery <= 0){
                throw new IllegalStateException("Заряд должен быть больше 0");
            }
            if (batteryLevel > maxBattery){
                batteryLevel = maxBattery;
            }
            return new EnergySystem(this);
        }
    }

}
