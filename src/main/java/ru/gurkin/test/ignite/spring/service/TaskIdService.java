package ru.gurkin.test.ignite.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gurkin.test.ignite.spring.model.TaskId;
import ru.gurkin.test.ignite.spring.repository.TaskIdRepository;

import java.util.Optional;


//@Service
public class TaskIdService {

    private TaskIdRepository repository;

    @Autowired
    public TaskIdService(TaskIdRepository repository){
        this.repository = repository;
    }

    public TaskId getTaskIdByJobId(String jobId){
        return Optional.ofNullable(repository.cache().get(Long.parseLong(jobId)))
                .orElseThrow(() -> new IllegalStateException("Task id for job " + jobId + " was not found"));
    }

    public void createTaskId(String jobId, String taskId){
        Long id = Long.parseLong(jobId);
        repository.cache().getAndPutIfAbsent (id, new TaskId(id, taskId));
    }

}
