import satelite.CommunicationSatellite;

public class Main {
    public static void main(String[] args) {
        CommunicationSatellite communicationSatellite = new CommunicationSatellite(
                "LEx-a228",
                99,
                1000.0 );

        communicationSatellite.sendData(10);
    }
}