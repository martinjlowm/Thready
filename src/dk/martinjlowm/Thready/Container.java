package dk.martinjlowm.Thready;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.swt.layout.GridData;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

/**
 * A classy container to keep track of divided GUI components. In this
 * case the start/pause button and the text area.
 * @author <a href="mailto:martin@martinjlowm.dk">Martin Jesper Low
 * Madsen</a>
 */
public class Container {
    private Button startPause;
    private Text textArea;
    private CounterThread counterThread;

    /**
     * The Container constructor which binds a thread to itself and
     * sets up both UI components.
     */
    public Container() {
        this.counterThread = new CounterThread(this);
        this.startPause = new Button(GUI.getShell(), SWT.PUSH);
        this.startPause.setText("Pause");
        this.startPause.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent e) {
                    counterThread.paused = !counterThread.paused;
                    startPause.setText(counterThread.paused ? "Start" : "Pause");

                    synchronized(counterThread) {
                        if (!counterThread.paused)
                            counterThread.notify();
                    }
                }
            });

        this.textArea = new Text(GUI.getShell(),
                                 SWT.MULTI |
                                 SWT.WRAP |
                                 SWT.V_SCROLL |
                                 SWT.BORDER);
        this.textArea.setLayoutData(new GridData(500, 150));
    }

    /**
     * Returns the thread tied to the current Container instance.
     * @return the thread.
     */
    public CounterThread getCounterThread() {
        return this.counterThread;
    }

    /**
     * Returns the text area.
     * @return the text area.
     */
    public Text getTextArea() {
        return this.textArea;
    }

    /**
     * Returns the start/pause button.
     * @return the start pause button.
     */
    public Button getStartPauseButton() {
        return this.startPause;
    }
}
