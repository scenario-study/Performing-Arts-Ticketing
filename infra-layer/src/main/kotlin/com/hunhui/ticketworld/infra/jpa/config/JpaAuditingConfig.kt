package com.hunhui.ticketworld.infra.jpa.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@EnableJpaAuditing
internal class JpaAuditingConfig
