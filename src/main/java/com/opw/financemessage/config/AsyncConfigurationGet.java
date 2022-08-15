package com.opw.financemessage.config;

import com.opw.financemessage.factory.SystemParameters;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync(proxyTargetClass = true)
public class AsyncConfigurationGet {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncConfigurationSend.class);

    @Bean(name = "getTaskExecutor")
    public Executor taskExecutor() {
        LOGGER.debug("Creating Async Task Executor");
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        SystemParameters parameters = new SystemParameters();
        int coreThread = (int)(long)((JSONObject)parameters.getSystemParameters().get("getThread")).get("coreThread");
        int maxThread = (int)(long)((JSONObject)parameters.getSystemParameters().get("getThread")).get("maxThread");
        int queue = (int)(long)((JSONObject)parameters.getSystemParameters().get("getThread")).get("queue");
        executor.setCorePoolSize(coreThread);
        executor.setMaxPoolSize(maxThread);
        executor.setQueueCapacity(queue);
        executor.setThreadNamePrefix("GetThread-");
        executor.initialize();
        return executor;
    }
}
