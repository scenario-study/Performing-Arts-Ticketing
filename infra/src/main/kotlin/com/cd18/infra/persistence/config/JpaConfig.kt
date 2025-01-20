package com.cd18.infra.persistence.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["com.cd18.infra.persistence.repository.jpa"])
class JpaConfig
