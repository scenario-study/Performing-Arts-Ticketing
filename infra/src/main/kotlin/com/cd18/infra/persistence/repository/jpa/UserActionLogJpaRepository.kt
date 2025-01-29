package com.cd18.infra.persistence.repository.jpa

import com.cd18.infra.persistence.model.UserActionLog
import org.springframework.data.jpa.repository.JpaRepository

interface UserActionLogJpaRepository : JpaRepository<UserActionLog, Long>
