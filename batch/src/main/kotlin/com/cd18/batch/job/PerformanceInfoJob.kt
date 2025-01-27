package com.cd18.batch.job

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PerformanceInfoJob : DefaultBatchConfiguration() {
    @Bean
    fun performanceViewCountStaticsJob(
        jobRepository: JobRepository,
        generateHourlyViewCount: Step,
    ): Job {
        return JobBuilder("performanceViewCountStaticsJob", jobRepository)
            .start(generateHourlyViewCount)
//            TODO : Add next step
            .build()
    }
}
