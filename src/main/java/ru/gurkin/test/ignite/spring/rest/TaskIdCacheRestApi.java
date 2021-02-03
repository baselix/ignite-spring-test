package ru.gurkin.test.ignite.spring.rest;

import org.springframework.web.bind.annotation.*;
import ru.gurkin.test.ignite.spring.model.TaskId;
import ru.gurkin.test.ignite.spring.service.TaskIdClientCacheService;
import ru.gurkin.test.ignite.spring.service.TaskIdService;

@RestController
public class TaskIdCacheRestApi {

    private TaskIdClientCacheService service;

    public TaskIdCacheRestApi(TaskIdClientCacheService service){
        this.service = service;
    }

    @GetMapping("/taskidcache")
    public void getTaskIdByJobId(){
        System.out.println(service.get(1L));
    }

    @PostMapping("/taskidcache")
    public void createTaskIdByJobId(){
        service.put(1L, "123");
        System.out.println(service.get(1L));
    }
}
