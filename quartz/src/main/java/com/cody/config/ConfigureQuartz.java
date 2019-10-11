package com.cody.config;

import java.io.IOException;
import java.util.Properties;

import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;

/**
 * quartz核心配置类
 * <p>
 * 代码描述
 * <p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved.
 * <p>
 *
 * @author WQL
 * @since 2019年10月11日 0011 11:26
 */
@Configuration
public class ConfigureQuartz {

    /**
     * 配置JobFactory 任务工厂实例
     * 
     * @param applicationContext
     * @return
     */
    @Bean
    public JobFactory jobFactor(ApplicationContext applicationContext) {
        // 采用自定义任务工厂，整合Spring实例来完成构建的任务
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    /**
     * SchedulerFactoryBean这个类的真正作用提供了对org.quartz.Scheduler的创建与配置，并且会管理它的生命周期与Spring同步 org.quartz.Scheduler:
     * 调度器。所有的调度都是由它控制。
     *
     * @param dataSource
     *            为SchedulerFactory配置数据源
     * @param jobFactory
     *            为SchedulerFactory配置JobFactory
     * @return
     * @throws IOException
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource, JobFactory jobFactory) throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        // 可选，QuartzScheduler启动时更新已存在的Job，这样就不用每次修改targetObject后删除quartz_job_details表对应记录
        factory.setOverwriteExistingJobs(true);
        // 项目启动完成后，等待两秒后开始执行调度器初始化
        factory.setStartupDelay(2);
        // 设置自行启动
        factory.setAutoStartup(true);
        // 设置数据源，使用与项目统一数据源
        factory.setDataSource(dataSource);
        // 将Spring管理Job自定义工厂交由调度器维护
        factory.setJobFactory(jobFactory);
        factory.setQuartzProperties(quartzProperties());
        // 覆盖已存在的任务
        factory.setOverwriteExistingJobs(true);
        return factory;
    }

    /**
     * 从quartz.properties文件中读取Quartz配置属性
     * 
     * @return
     * @throws IOException
     */
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    /**
     * 配置JobFactory，为quartz作业添加自动连接支持
     */
    public final class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

        private transient AutowireCapableBeanFactory beanFactory;

        @Override
        protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
            final Object job = super.createJobInstance(bundle);
            // 将Job实例交付给Spring IOC
            beanFactory.autowireBean(job);
            return job;
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) {
            beanFactory = applicationContext.getAutowireCapableBeanFactory();
        }
    }
}
