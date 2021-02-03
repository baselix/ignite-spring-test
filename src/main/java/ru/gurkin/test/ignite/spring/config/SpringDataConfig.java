package ru.gurkin.test.ignite.spring.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.ClientConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.springdata22.repository.config.EnableIgniteRepositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.QueryLookupStrategy;
import ru.gurkin.test.ignite.spring.model.TaskId;


@Configuration
//@EnableIgniteRepositories(value="ru.gurkin.test.ignite.spring.repository",queryLookupStrategy = QueryLookupStrategy.Key.CREATE)
public class SpringDataConfig {

    private static final Logger logger = LoggerFactory.getLogger(SpringDataConfig.class);

    private static final String CACHE_NAME = "thin-cache";

//    @Bean
    public Ignite igniteInstance() {
        IgniteConfiguration cfg = new IgniteConfiguration();
        CacheConfiguration cache = new CacheConfiguration(CACHE_NAME);
        cache.setIndexedTypes(Long.class, TaskId.class);
        cfg.setCacheConfiguration(cache);
        cfg.setClientMode(true);

        TcpDiscoverySpi spi = new TcpDiscoverySpi();
        spi.setClientReconnectDisabled(false);
        cfg.setDiscoverySpi(spi);
        return Ignition.start(cfg);
    }
}
