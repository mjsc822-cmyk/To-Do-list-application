package ui;

import controller.TaskManager;
import model.Task;
import model.TaskStatus;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskF extends JFrame implements ActionListener {


    private TopMain parent;
    private TaskManager m;

    private JTextField idField;
    private JTextField nameField;
    private JTextArea descArea;
    private JComboBox<TaskStatus> statusBox;
    private JButton saveButton;

    public TaskF(TopMain parent, TaskManager m) {
        this.parent = parent;
        this.m = m;

        setTitle("Add Task");
        setSize(400, 300);
        setLayout(null); 
        setLocation(650, 510);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        
        JLabel idLabel = new JLabel("Task ID");
        idLabel.setBounds(10, 15, 200, 20);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(15, 35, 20, 25);
        idField.setEditable(false);
        idField.setText(String.valueOf(m.generateTaskId()));
        add(idField);

      
        JLabel nameLabel = new JLabel("Task Name");
        nameLabel.setBounds(10, 60, 80, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(15, 85, 300, 25);
        add(nameField);

        
        JLabel descLabel = new JLabel("Task Description");
        descLabel.setBounds(10, 110, 100, 25);
        add(descLabel);

        descArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(descArea);
        scroll.setBounds(15, 135, 300, 30);
        add(scroll);

      
        JLabel statusLabel = new JLabel("Status");
        statusLabel.setBounds(109, 167, 80, 25);
        add(statusLabel);

        statusBox = new JComboBox<>(TaskStatus.values());
        statusBox.setBounds(110, 190, 150, 30);
        add(statusBox);

       
        saveButton = new JButton("Save Task");
        saveButton.setBounds(135, 226, 100, 30);
        saveButton.addActionListener(this);
        add(saveButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {

            if (nameField.getText().isEmpty() || descArea.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in Task Name and Status", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Task task = new Task(
                    Integer.parseInt(idField.getText()),
                    nameField.getText(),
                    descArea.getText(),
                    (TaskStatus) statusBox.getSelectedItem()
            );

            m.addTask(task);
            parent.refreshTable();
            dispose();
        }
    }
}