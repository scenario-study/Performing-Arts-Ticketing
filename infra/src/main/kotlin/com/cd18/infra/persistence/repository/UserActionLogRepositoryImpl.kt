package com.cd18.infra.persistence.repository

import com.cd18.domain.metrics.dto.TargetCountSummaryDto
import com.cd18.domain.metrics.enums.ActionType
import com.cd18.domain.metrics.enums.TargetType
import com.cd18.domain.metrics.repository.UserActionLogRepository
import com.cd18.infra.persistence.model.QUserActionLog.userActionLog
import com.cd18.infra.persistence.model.UserActionLog
import com.cd18.infra.persistence.repository.jpa.UserActionLogJpaRepository
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class UserActionLogRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
    private val userActionLogJpaRepository: UserActionLogJpaRepository,
) : UserActionLogRepository {
    override fun save(
        userId: Long?,
        actionType: ActionType,
        targetId: Long?,
        targetType: TargetType,
        actionDetail: String?,
    ) {
        userActionLogJpaRepository.save(
            UserActionLog(
                userId = userId,
                actionType = actionType,
                targetId = targetId,
                targetType = targetType,
                actionDetail = actionDetail,
            ),
        )
    }

    override fun getTargetIdCountSummary(
        userId: Long?,
        actionType: ActionType?,
        targetType: TargetType?,
        startTime: LocalDateTime?,
        endTime: LocalDateTime?,
    ): List<TargetCountSummaryDto> {
        return queryFactory.select(
            Projections.constructor(
                TargetCountSummaryDto::class.java,
                userActionLog.targetType,
                userActionLog.actionType,
                userActionLog.targetId,
                userActionLog.count(),
            ),
        ).from(userActionLog)
            .where(
                userId?.let { userActionLog.userId.eq(it) },
                actionType?.let { userActionLog.actionType.eq(it) },
                targetType?.let { userActionLog.targetType.eq(it) },
                startTime?.let { userActionLog.createdAt.goe(it) },
                endTime?.let { userActionLog.createdAt.loe(it) },
            )
            .groupBy(userActionLog.targetType, userActionLog.actionType, userActionLog.targetId)
            .fetch()
    }
}
