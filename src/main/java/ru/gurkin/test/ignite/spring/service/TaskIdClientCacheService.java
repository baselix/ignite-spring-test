package ru.gurkin.test.ignite.spring.service;

import org.apache.ignite.Ignition;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class TaskIdClientCacheService {

    private static final Logger log = LoggerFactory.getLogger(TaskIdClientCacheService.class);

    private static final String HOST = "127.0.0.1";
    private static final String PORT = "10800";
    private static final String CACHE_NAME = "simple-cache";

    private IgniteClient client;
    private ClientCache<Long, String> cache;

    @Autowired
    public TaskIdClientCacheService(){
        //empty
    }

    @PostConstruct
    public void init(){
        log.info("Init cache");
        initClient();
        getCache();
    }

    public void initClient(){
        try {
            ClientConfiguration cfg = new ClientConfiguration();
            cfg.setAddresses(HOST + ":" + PORT);
            this.client = Ignition.startClient(cfg);
            log.info("Ignite client was initialized");
        } catch (Exception e){
            log.error("Error while connecting to ignite");
        }
    }

    @PreDestroy
    public void destroy() throws Exception {
        this.client.close();
        log.info("Destory ignite client");
    }

    private void getCache(){
        this.cache = client.getOrCreateCache(CACHE_NAME);
    }

    private void reconnect(){
        init();
    }

    public String get(Long jobId){
        try {
            return cache.get(jobId);
        } catch (Exception e){
            reconnect();
            return get(jobId);
        }
    }

    public String put(Long id, String taskId){
        try {
            return cache.getAndPut(id, taskId);
        } catch (Exception e){
            reconnect();
            return put(id, taskId);
        }
    }
}
