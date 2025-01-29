package com.cd18.application.metrics.impl

import com.cd18.application.metrics.UserActionLogService
import com.cd18.domain.metrics.enums.ActionType
import com.cd18.domain.metrics.enums.TargetType
import com.cd18.domain.metrics.repository.UserActionLogRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserActionLogServiceImpl(
    private val userActionLogRepository: UserActionLogRepository,
) : UserActionLogService {
    @Transactional
    override fun logAction(
        userId: Long?,
        actionType: ActionType,
        targetType: TargetType,
        targetId: Long?,
        actionDetail: String?,
    ) {
        userActionLogRepository.save(
            userId = userId,
            actionType = actionType,
            targetType = targetType,
            targetId = targetId,
            actionDetail = actionDetail,
        )
    }
}
