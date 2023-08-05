package dk.kudishin.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tasks")
public class Task {

    @Id
    @GeneratedValue
    private int id;
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Task() {
    }

    public Task(String desc) {
        this.desc = desc;
    }

    public Task(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }
}