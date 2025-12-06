import satelite.CommunicationSatellite;
import satelite.ImagingSatelite;

public class Main {
    public static void main(String[] args) {
        CommunicationSatellite communicationSatellite = new CommunicationSatellite(
                "LEx-a228",
                99,
                1000.0 );

        ImagingSatelite imagingSatelite = new ImagingSatelite(
                "Ero2*-433",
                75.0,
                1440.0);

        communicationSatellite.activate();
        communicationSatellite.sendData(10);

        imagingSatelite.activate();
        imagingSatelite.TakePhoto();
        imagingSatelite.TakePhoto();
    }
}