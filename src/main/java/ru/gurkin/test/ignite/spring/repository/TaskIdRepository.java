package ru.gurkin.test.ignite.spring.repository;

import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;
import ru.gurkin.test.ignite.spring.model.TaskId;

import javax.cache.Cache;
import java.util.List;

//@RepositoryConfig(cacheName = "thin-cache")
public interface TaskIdRepository extends IgniteRepository<TaskId, Long> {
    public List<Cache.Entry<String,TaskId>> findByIdGreaterThanEqualOrderByIdDesc(Long id);
}
