package dk.kudishin.simpletasklist.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "taskdesc")
    private String taskDesc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String desc) {
        this.taskDesc = desc;
    }

    public Task() {
    }

    public Task(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public Task(int id, String taskDesc) {
        this.id = id;
        this.taskDesc = taskDesc;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskDesc='" + taskDesc + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        return taskDesc.equals(task.taskDesc);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + taskDesc.hashCode();
        return result;
    }
}