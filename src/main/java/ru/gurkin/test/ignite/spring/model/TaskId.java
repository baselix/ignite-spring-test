package ru.gurkin.test.ignite.spring.model;

import java.io.Serializable;
import java.util.Objects;

public class TaskId implements Serializable {

    private Long id;

    private String instance;

    public TaskId(){}

    public TaskId(Long id, String taskId){
        setId(id);
        set(taskId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String get(){
        return instance;
    }

    public void set(String taskId){
        this.instance = taskId;
    }

    @Override
    public String toString() {
        return "TaskId{" +
                "id=" + id +
                ", instance='" + instance + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskId taskId = (TaskId) o;
        return Objects.equals(id, taskId.id) && Objects.equals(instance, taskId.instance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, instance);
    }
}
