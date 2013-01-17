package dk.martinjlowm.Thready;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.swt.layout.GridData;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

public class Container {
    private Button startPause;
    private Text textArea;
    private CounterThread counterThread;

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

    public CounterThread getCounterThread() {
        return this.counterThread;
    }

    public Text getTextArea() {
        return this.textArea;
    }

    public Button getStartPauseButton() {
        return this.startPause;
    }
}
