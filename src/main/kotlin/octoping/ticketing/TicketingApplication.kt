package octoping.ticketing

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.scheduling.annotation.EnableScheduling

@ConfigurationPropertiesScan
@EnableScheduling
@EnableJpaAuditing
@EnableCaching
@SpringBootApplication
class TicketingApplication

fun main(args: Array<String>) {
    runApplication<TicketingApplication>(*args)
}
