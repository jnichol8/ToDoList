import javax.swing.*;
import java.awt.*;

class TaskCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        Task task = (Task) value;

        // Change text color based on completion status
        if (task.getIsDone()) {
            setForeground(Color.gray); // Gray for completed tasks
        } else {
            setForeground(Color.BLACK); // Black for pending tasks
        }

        return this;
    }
}