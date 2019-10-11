package com.cody.config;
import	java.beans.Beans;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonFactory;
import org.quartz.SchedulerFactory;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
     * 配置JobFactory
     * @param applicationContext
     * @return
     */
    public JobFactory jobFactor(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource, JobFactory jobFactory) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        // 可选，QuartzScheduler启动时更新已存在的Job，这样就不用每次修改targetObject后删除quartz_job_details表对应记录
        factory.setOverwriteExistingJobs(true);
        // 设置自行启动
        factory.setAutoStartup(true);
        factory.setDataSource(dataSource);
        factory.setJobFactory(jobFactory);
        factory.setQuartzProperties();
    }

    public Properties quartzProperties() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation();
    }



    /**
     * 配置JobFactory，为quartz作业添加自动连接支持
     */
    public final class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {
        private AutowireCapableBeanFactory beanFactory;

        @Override
        protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
            final Object job = super.createJobInstance(bundle);
            beanFactory.autowireBean(job);
            return job;
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) {
            beanFactory = applicationContext.getAutowireCapableBeanFactory();
        }
    }
}
