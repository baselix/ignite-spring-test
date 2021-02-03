package ru.gurkin.test.ignite.spring.rest;

import org.springframework.web.bind.annotation.*;
import ru.gurkin.test.ignite.spring.model.TaskId;
import ru.gurkin.test.ignite.spring.service.TaskIdService;

//@RestController
public class TaskIdRestApi {

    private TaskIdService service;

    public TaskIdRestApi(TaskIdService service){
        this.service = service;
    }

    @GetMapping("/taskid/{id}")
    public TaskId getTaskIdByJobId(@PathVariable("id") String jobId){
        return service.getTaskIdByJobId(jobId);
    }

    @PostMapping("/taskid")
    public TaskId createTaskIdByJobId(@RequestBody String body){
        String[] parts = body.split(":");
        service.createTaskId(parts[0], parts[1]);
        return service.getTaskIdByJobId(parts[0]);
    }
}
