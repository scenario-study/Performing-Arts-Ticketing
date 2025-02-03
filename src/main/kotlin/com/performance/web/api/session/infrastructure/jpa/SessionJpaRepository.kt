package com.performance.web.api.session.infrastructure.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface SessionJpaRepository: JpaRepository<SessionEntity, Long> {

}
