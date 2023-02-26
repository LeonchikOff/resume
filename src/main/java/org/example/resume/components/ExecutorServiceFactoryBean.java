package org.example.resume.components;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component(value = "defaultExecutorService")
public class ExecutorServiceFactoryBean implements FactoryBean<ExecutorService> {
    private ExecutorService executorService;
    public static final String AUTO = "AUTO";
    private int threadCount;
    private boolean autoThreadCount;

    @Value(value = "${executorService.threadCount}")
    public void setThreadCount(String threadCount) {
        if (threadCount.trim().equals(AUTO)) {
            autoThreadCount = true;
        } else {
            this.threadCount = Integer.parseInt(threadCount);
            if (this.threadCount <= 0) autoThreadCount = true;
        }
    }

    @PostConstruct
    private void postConstruct() {
        executorService = !autoThreadCount ? Executors.newFixedThreadPool(threadCount) : Executors.newCachedThreadPool();
    }

    @Override
    public ExecutorService getObject() throws Exception {
        return executorService;
    }

    @Override
    public Class<?> getObjectType() {
        return ExecutorService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @PreDestroy
    private void preDestroy() {
        executorService.shutdown();
    }
}
