package dk.martinjlowm.Thready;

/**
 * The main class which spawns `NUM_CONTAINERS' containers which each
 * have its own thread.
 * @author <a href="mailto:martin@martinjlowm.dk">Martin Jesper Low
 * Madsen</a>
 */
public class Thready {
    public static final String APP_NAME = "Thready";
    public static final int NUM_CONTAINERS = 5;

    /**
     * Initializes the GUI and starts a counting thread for each
     * container.
     * @param args array of arguments as provided by the user.
     */
    public static void main(String[] args) {
        GUI.init();

        for (int i = 1; i <= NUM_CONTAINERS; i++)
            (new Container()).getCounterThread().start();

        GUI.show();

        GUI.waitForDisposal();
    }
}
