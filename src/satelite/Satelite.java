package satelite;

public abstract class Satelite{
    private String name;
    private Boolean isActive;
    private double batteryLevel;

    public Satelite(String name, double batteryLevel) {
        this.name = name;
        this.batteryLevel = batteryLevel;
        this.isActive = false;
    }

    public boolean activate(){
        if (batteryLevel > 0.20){
            return  isActive = true;
        }
        return false;
    }

    public void deactivate(){
        if (isActive){ isActive = false; }
    }

    public void consumeBattery(double amount){
        batteryLevel -= amount;
        if (batteryLevel <= 0.2){
            deactivate();
        }
    }

    protected abstract void performMission();

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public Boolean isActive() {
        return isActive;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s (Заряд: %d%%)", name, (int)(batteryLevel * 100));
    }
}
