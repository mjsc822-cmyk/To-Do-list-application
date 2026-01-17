package model;

public class Task extends AbstractTask {
    private TaskStatus status;

    public Task() {
    	
        this.status = TaskStatus.NOT_STARTED;
    }
    
    public Task(int taskId, String taskName, String taskDescription, TaskStatus status) {
        super(taskId, taskName, taskDescription);
        this.status = status;
    }

    @Override
    public TaskStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
