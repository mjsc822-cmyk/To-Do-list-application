package controller;
import java.util.ArrayList;
import model.Task;
import model.TaskStatus;

public class TaskManager {

    private ArrayList<Task> tasks;
    private int nextId;

    public TaskManager() {
        tasks = new ArrayList<>();
        nextId = 1;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int generateTaskId() {
        return nextId++;
    }
    public boolean updateTaskStatus(int taskId, TaskStatus newStatus) {
        for (Task t : tasks) {
            if (t.getTaskId() == taskId) {
                t.setStatus(newStatus);
                return true;
            }
        }
        return false; 
    }

}