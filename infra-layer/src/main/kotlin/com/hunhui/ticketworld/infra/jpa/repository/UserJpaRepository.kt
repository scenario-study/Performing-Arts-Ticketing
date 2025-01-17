package com.hunhui.ticketworld.infra.jpa.repository

import com.hunhui.ticketworld.infra.jpa.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

internal interface UserJpaRepository : JpaRepository<UserEntity, UUID>
