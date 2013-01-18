package dk.martinjlowm.Thready;

import org.eclipse.swt.SWT;

import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Display;

/**
 * A singleton class that controls the basics of the user
 * interface.
 * @author <a href="mailto:martin@martinjlowm.dk">Martin Jesper Low
 * Madsen</a>
 */
public class GUI {
    private static Shell shell;

    private GUI() {}            // Singleton

    /**
     * Initiates the user interface by creating its shell and sets the
     * layout manager.
     */
    public static void init() {
        // Invert resize bits from a trimmed shell.
        shell = new Shell(Display.getDefault(), SWT.SHELL_TRIM & (~SWT.RESIZE));

        setLayout();
    }

    /**
     * Sets the GUI layout and changes the default properties.
     */
    private static void setLayout() {
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.marginWidth = 10;
        layout.marginHeight = 10;
        layout.horizontalSpacing = 5;

        shell.setLayout(layout);
    }

    /**
     * Displays the actual window.
     */
    public static void show() {
        shell.setText(Thready.APP_NAME);
        shell.pack();
        shell.open();
    }

    /**
     * Waits for the window to be closed and then terminates the
     * program.
     */
    public static void waitForDisposal() {
        Display display = Display.getDefault();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }

    /**
     * Returns the main GUI shell.
     * @return the GUI shell.
     */
    public static Shell getShell() {
        return shell;
    }
}
