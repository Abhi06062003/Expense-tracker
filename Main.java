import javax.swing.*;
import ui.ExpenseTrackerUI;

public class Main {
    public static void main(String[] args) {
        // Run the UI in the Event Dispatch Thread (good practice in Swing)
        SwingUtilities.invokeLater(() -> {
            ExpenseTrackerUI tracker = new ExpenseTrackerUI();
            tracker.setVisible(true);
        });
    }
}
