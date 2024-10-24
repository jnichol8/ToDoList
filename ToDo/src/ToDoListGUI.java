import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ToDoListGUI {
    private ToDoList todoList;
    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskList;
    private JTextField taskInput;
    private JTextField descInput;
    private JTextField timeInput;
    private JTextArea descriptionArea;
    private JLabel timeLabel;
    private JComboBox<String> categoryFilter;
    
    private static final String ALL = "All";
    private static final String QUICK = "Quick (0-15 mins)";
    private static final String MEDIUM = "Medium (16-30 mins)";
    private static final String LONG = "Long (31+ mins)";
    
    public ToDoListGUI() {
        todoList = new ToDoList();
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskInput = createTextField("task", 15);
        descInput = createTextField("description", 20);
        timeInput = createTextField("time (mins)", 10);

        descriptionArea = new JTextArea(5, 30);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        timeLabel = new JLabel("Time: ");

        setupGUI();
    }
    
    private JTextField createTextField(String placeholder, int len) {
        JTextField textField = new JTextField(len);
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });

        return textField;
    }

    private void setupGUI() {
        JFrame frame = new JFrame("To-Do List Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1150, 700);
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
        JButton editButton = new JButton("Edit Task");
        inputPanel.add(editButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        
        categoryFilter = new JComboBox<>(new String[]{ALL, QUICK, MEDIUM, LONG});
        inputPanel.add(categoryFilter);
        categoryFilter.addActionListener(e -> updateTaskList());

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
                    clearInputs();
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
        
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                String taskName = taskInput.getText();
                String taskDescription = descInput.getText();
                String temp = timeInput.getText();
                int taskTime = 0;
                try {
                    taskTime = Integer.valueOf(temp);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
                if (selectedIndex != -1) {
                    todoList.editTask(selectedIndex, taskName, taskDescription, taskTime);
                    taskListModel.set(selectedIndex, todoList.getTask(selectedIndex)); // Refresh display
                    
                    Task updatedTask = taskListModel.getElementAt(selectedIndex);
                    descriptionArea.setText(updatedTask.getDesc()+ "\nCategory: " + updatedTask.getCatagory());
                    timeLabel.setText("Time: " + updatedTask.getTime() + " mins");
                    
                    clearInputs();
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
                        descriptionArea.setText(selectedTask.getDesc() + "\nCategory: " + selectedTask.getCatagory());
                        timeLabel.setText("Time: " + selectedTask.getTime() + " mins");
                    }
                }
            }
        });

        frame.setVisible(true);
    }
    
    private void updateTaskList() {
        String selectedCategory = (String) categoryFilter.getSelectedItem();
        taskListModel.clear();
        for (Task task : todoList.getTasks()) {
            if (matchesCategory(task, selectedCategory)) {
                taskListModel.addElement(task);
            }
        }
    }
    
    private boolean matchesCategory(Task task, String category) {
        switch (category) {
            case QUICK:
                return task.getTime() <= 15;
            case MEDIUM:
                return task.getTime() > 15 && task.getTime() <= 30;
            case LONG:
                return task.getTime() > 30;
            case ALL:
            default:
                return true;
        }
    }
    
    private void clearInputs() {
        taskInput.setText("task");
        taskInput.setForeground(Color.GRAY);
        descInput.setText("description");
        descInput.setForeground(Color.GRAY);
        timeInput.setText("time (mins)");
        timeInput.setForeground(Color.GRAY);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToDoListGUI());
    }
}
