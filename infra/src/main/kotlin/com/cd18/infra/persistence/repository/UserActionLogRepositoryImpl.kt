package com.cd18.infra.persistence.repository

import com.cd18.domain.metrics.enums.ActionType
import com.cd18.domain.metrics.enums.TargetType
import com.cd18.domain.metrics.repository.UserActionLogRepository
import com.cd18.infra.persistence.model.UserActionLog
import com.cd18.infra.persistence.repository.jpa.UserActionLogJpaRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

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
}
