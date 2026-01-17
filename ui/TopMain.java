package ui;
import controller.TaskManager;
import model.Task;
import model.TaskStatus;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopMain extends JFrame implements ActionListener {

    private TaskManager m;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton addButton;
    private JButton changeStatusButton;
    private TaskF taskF;

    public TopMain(TaskManager m) {
        this.m = m;

        setTitle("To-Do List");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addButton = new JButton("Add Task");
        addButton.addActionListener(this);

        changeStatusButton = new JButton("Change Status");
        changeStatusButton.addActionListener(this);

        JPanel topPanel = new JPanel();
        topPanel.add(addButton);
        topPanel.add(changeStatusButton);

        tableModel = new DefaultTableModel(
                new String[]{"Task ID", "Task Name", "Task Description", "Status"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        refreshTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            if (taskF == null || !taskF.isDisplayable()) {
                taskF = new TaskF(this, m);
                taskF.setVisible(true);
            }
        } else if (e.getSource() == changeStatusButton) {

            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "select a task first.", "No Task Selected", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int taskId = (Integer) table.getValueAt(selectedRow, 0);

            JComboBox<TaskStatus> combo = new JComboBox<>(TaskStatus.values());
            int option = JOptionPane.showConfirmDialog(this, combo, "Change Status", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (option == JOptionPane.OK_OPTION) {
                TaskStatus selectedStatus = (TaskStatus) combo.getSelectedItem();
                boolean updated = m.updateTaskStatus(taskId, selectedStatus);
                if (updated) {
                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update status. Task not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void refreshTable() {
        tableModel.setRowCount(0);

        for (Task t : m.getTasks()) {
            tableModel.addRow(new Object[]{
                    t.getTaskId(),
                    t.getTaskName(),
                    t.getTaskDescription(),
                    t.getStatus()
            });
        }
    }
}

