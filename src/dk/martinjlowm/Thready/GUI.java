package dk.martinjlowm.Thready;

import org.eclipse.swt.SWT;

import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Display;

public class GUI {
    private static Shell shell;

    private GUI() {}            // Singleton

    public static void init() {
        // Invert resize bits from a trimmed shell.
        shell = new Shell(Display.getDefault(), SWT.SHELL_TRIM & (~SWT.RESIZE));

        setLayout();
    }

    private static void setLayout() {
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.marginWidth = 10;
        layout.marginHeight = 10;
        layout.horizontalSpacing = 5;

        shell.setLayout(layout);
    }

    public static void show() {
        shell.setText(Thready.APP_NAME);
        shell.pack();
        shell.open();
    }

    public static void waitForDisposal() {
        Display display = Display.getDefault();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }

    public static Shell getShell() {
        return shell;
    }
}
