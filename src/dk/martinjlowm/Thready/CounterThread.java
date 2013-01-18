package dk.martinjlowm.Thready;

import java.lang.Thread;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Display;

/**
 * A thread belonging to the Container class which periodically
 * appends a number to the Container's text area. The thread may be
 * suspended using the toggling start/pause button.
 * @author <a href="mailto:martin@martinjlowm.dk">Martin Jesper Low
 * Madsen</a>
 */
public class CounterThread extends Thread {
    private static ArrayList<CounterThread> INSTANCES = new ArrayList<CounterThread>();
    private int id;
    private int count;
    private int sleepDelay;
    public volatile boolean paused;
    private Container container;

    /**
     * Constructs a new CounterThread, sets its instance variables and
     * adds it to the static `INSTANCES' list.
     * @param container the GUI container which the thread belongs to.
     */
    public CounterThread(Container container) {
        this.id = INSTANCES.size();
        this.count = 0;
        this.sleepDelay = (this.id + 1) * 250;
        this.paused = false;
        this.container = container;

        INSTANCES.add(this);
    }

    /**
     * Returns the instance of CounterThread which has the specified id.
     * @param id the id to look up.
     * @return the instance matching the id.
     */
    public static CounterThread getCounterThreadByID(int id) {
        return INSTANCES.get(id);
    }

    /**
     * Returns the list of CounterThread instances.
     * @return a list of CounterThread instances.
     */
    public static ArrayList<CounterThread> getCounterThreads() {
        return INSTANCES;
    }

    /**
     * Sets the delay between each thread execution.
     * @param delay the delay in milliseconds.
     */
    public void setSleepDelay(int delay) {
        this.sleepDelay = delay;
    }

    /**
     * The action each thread executes. It appends the current `count'
     * value to the text area and increments it.
     */
    public void run() {
        while (true) {
            try {
                Thread.sleep(this.sleepDelay);

                if (this.paused) {
                    synchronized(this) {
                        while (this.paused) {
                            this.wait();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Display.getDefault().asyncExec(new Runnable () {
                    public void run () {
                        container.getTextArea().append(""+count+" ");
                    }
                });
            this.count++;
        }
    }
}
