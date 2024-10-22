import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoListGUI {
    private ToDoList todoList;
    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskList;
    private JTextField taskInput;
    private JTextField descInput;
    private JTextField timeInput;
    private JTextArea descriptionArea;
    private JLabel timeLabel;

    public ToDoListGUI() {
        todoList = new ToDoList();
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskInput = new JTextField("task", 15);
        descInput = new JTextField("description", 20);
        timeInput = new JTextField("time (mins)", 10);

        descriptionArea = new JTextArea(5, 30);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        timeLabel = new JLabel("Time: ");

        setupGUI();
    }

    private void setupGUI() {
        JFrame frame = new JFrame("To-Do List Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(950, 700);
        frame.setLayout(new BorderLayout());

        // Task Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.add(taskInput);
        inputPanel.add(descInput);
        inputPanel.add(timeInput);
        JButton addButton = new JButton("Add Task");
        inputPanel.add(addButton);
        JButton removeButton = new JButton("Remove Task");
        inputPanel.add(removeButton);
        JButton completeButton = new JButton("Complete Task");
        inputPanel.add(completeButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Task List Panel
        JScrollPane scrollPane = new JScrollPane(taskList);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Labels Panel
        JPanel labelsPanel = new JPanel(new BorderLayout());
        labelsPanel.add(new JScrollPane(descriptionArea), BorderLayout.CENTER);
        labelsPanel.add(timeLabel, BorderLayout.SOUTH);
        frame.add(labelsPanel, BorderLayout.SOUTH);

        taskList.setCellRenderer(new TaskCellRenderer());

        // Button Actions
        // Handle the add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskName = taskInput.getText();
                String taskDescription = descInput.getText();
                String temp = timeInput.getText();
                int taskTime = 0;
                try {
                    taskTime = Integer.valueOf(temp);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }

                if (!taskDescription.isEmpty() && taskTime != 0) {
                    todoList.addTask(taskName, taskDescription, taskTime);
                    taskListModel.addElement(new Task(taskName, taskDescription, taskTime));
                    taskInput.setText("");
                    descInput.setText("");
                    timeInput.setText("");
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    todoList.deleteTask(selectedIndex);
                    taskListModel.remove(selectedIndex);
                    descriptionArea.setText("");
                    timeLabel.setText("Time: ");
                }
            }
        });

        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    todoList.markTaskDone(selectedIndex);
                    taskListModel.set(selectedIndex, todoList.getTask(selectedIndex)); // Refresh display
                }
            }
        });

        // Add ListSelectionListener to taskList
        taskList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = taskList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        Task selectedTask = taskListModel.getElementAt(selectedIndex);
                        descriptionArea.setText(selectedTask.getDesc());
                        timeLabel.setText("Time: " + selectedTask.getTime() + " mins");
                    }
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToDoListGUI());
    }
}
