package satelite;

public class SatelliteState {
    private boolean isActive;

    public SatelliteState() {
        this.isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }

    public void activate(){
        isActive = true;
    }

    public void deactivate(){
        isActive = false;
    }
}
