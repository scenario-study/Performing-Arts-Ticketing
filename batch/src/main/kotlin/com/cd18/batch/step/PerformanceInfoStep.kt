package com.cd18.batch.step

import com.cd18.batch.task.PerformanceHourlyViewCountTask
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class PerformanceInfoStep : DefaultBatchConfiguration() {
    @Bean
    fun generateHourlyViewCount(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        performanceHourlyViewCountTask: PerformanceHourlyViewCountTask,
    ): Step {
        return StepBuilder("generateHourlyViewCount", jobRepository)
            .tasklet(performanceHourlyViewCountTask, transactionManager)
            .build()
    }
}
