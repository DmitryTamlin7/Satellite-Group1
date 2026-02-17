package satelite;

public abstract class Satelite{
    private String name;
    protected SatelliteState state;
    protected  EnergySystem energy;

    public Satelite(String name, double batteryLevel) {
        this.name = name;
        this.energy = new EnergySystem(batteryLevel);
        this.state = new SatelliteState();
    }

    public boolean activate(){
        if (!energy.isCriticleLevel()){
            state.activate();
            return true;
        }
        return false;
    }

    public void deactivate(){
        state.deactivate();
    }

    protected void consumeEnergy(double amount){
        energy.consumeBattery(amount);
        if (energy.isCriticleLevel()){
            deactivate();
        }
    }

    protected abstract void performMission();

    public double getBatteryLevel() {
        return energy.getBatteryLevel();
    }

    public Boolean isActive() {
        return state.isActive();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s (Заряд: %d%%)", name, (int)(getBatteryLevel() * 100));
    }

    protected String getBaseDetails() {
        return String.format("name='%s', isActive=%s, batteryLevel=%.2f",
                name, state.isActive(), energy.getBatteryLevel());
    }
}
