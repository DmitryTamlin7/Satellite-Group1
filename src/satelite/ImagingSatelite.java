package satelite;

import java.nio.file.Path;

public class ImagingSatelite extends Satelite{

    private double resolution;
    private int photosTaken;

    public ImagingSatelite(String name, double batteryLevel, double resolution) {
        super(name, batteryLevel);
        this.resolution = resolution;
        this.photosTaken = 0;
    }

    public int getPhotosTaken() {
        return photosTaken;
    }

    public double getResolution() {
        return resolution;
    }

    @Override
    protected void performMission() {
        if (isActive()) {
            System.out.printf("%s: Сьемка территории с разрешением %.1f м.пиксель\n", getName(), getResolution());
            TakePhoto();
            consumeBattery(0.08);
        }else System.out.printf("❌ %s: сьемка невозможна - аппарат не активен\n", getName());
    }

    public void TakePhoto(){
        if (isActive()){
            photosTaken += 1;
            System.out.printf("Снимок: %s сделан!\n", getPhotosTaken());
        }
    }

    @Override
    public String toString() {
        return String.format("%s (Заряд: %d%%)", getName(), (int)(getBatteryLevel() * 100));
    }


}
