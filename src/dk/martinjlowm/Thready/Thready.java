package dk.martinjlowm.Thready;

public class Thready {
    public static final String APP_NAME = "Thready";
    public static final int NUM_CONTAINERS = 5;

    public static void main(String[] args) {
        GUI.init();

        for (int i = 1; i <= NUM_CONTAINERS; i++)
            (new Container()).getCounterThread().start();

        GUI.show();

        GUI.waitForDisposal();
    }
}
