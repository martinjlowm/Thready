package dk.martinjlowm.Thready;

import java.lang.Thread;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Display;

public class CounterThread extends Thread {
    private static ArrayList<CounterThread> INSTANCES = new ArrayList<CounterThread>();
    private int id;
    private int count;
    private int sleepDelay;
    public volatile boolean paused;
    private Container container;

    public CounterThread(Container container) {
        this.id = INSTANCES.size();
        this.count = 0;
        this.sleepDelay = (this.id + 1) * 250;
        this.paused = false;
        this.container = container;

        INSTANCES.add(this);
    }

    public CounterThread getCounterThreadByID(int id) {
        return INSTANCES.get(id);
    }

    public ArrayList<CounterThread> getCounterThreads() {
        return INSTANCES;
    }

    public void setSleepDelay(int delay) {
        this.sleepDelay = delay;
    }

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
