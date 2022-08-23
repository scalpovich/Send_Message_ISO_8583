package com.opw.financemessage.config;

import com.opw.financemessage.factory.SystemParameters;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync(proxyTargetClass = true)
public class AsyncConfigurationSend {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncConfigurationSend.class);
    @Autowired
    private SystemParameters parameters;

    @Bean(name = "sendTaskExecutor")
    public Executor taskExecutor() {
        LOGGER.debug("Creating Async Task Executor");
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int coreThread = parameters.getCoreSendThread();
        int maxThread = parameters.getMaxSendThread();
        int queue = parameters.getQueueSendThread();
        executor.setCorePoolSize(coreThread);
        executor.setMaxPoolSize(maxThread);
        executor.setQueueCapacity(queue);
        executor.setThreadNamePrefix("SendThread-");
        executor.initialize();
        return executor;
    }

}
