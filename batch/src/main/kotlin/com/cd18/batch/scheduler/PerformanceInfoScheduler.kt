package com.cd18.batch.scheduler

import com.cd18.batch.enums.ScheduleCron
import com.cd18.common.util.formattedTime
import com.cd18.common.util.getCurrentTime
import com.cd18.common.util.getStartOfHour
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.Scheduled
import java.time.LocalDateTime

@Configuration
class PerformanceInfoScheduler(
    private val jobLauncher: JobLauncher,
    private val performanceViewCountStaticsJob: Job,
) {
    private val log = KotlinLogging.logger {}

    @Scheduled(cron = ScheduleCron.EVERY_HOUR)
    fun runPerformanceInfoStaticsJob() {
        val targetTime: LocalDateTime = getCurrentTime().minusHours(1).getStartOfHour()
        val jobParameters =
            JobParametersBuilder()
                .addString("targetTime", targetTime.formattedTime())
                .addLong("time", System.currentTimeMillis())
                .toJobParameters()

        log.info { "Starting hourly statistics job for problem views. Target time: $targetTime" }
        jobLauncher.run(performanceViewCountStaticsJob, jobParameters)
    }
}
