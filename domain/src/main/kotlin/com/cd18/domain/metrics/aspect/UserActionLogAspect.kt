package com.cd18.domain.metrics.aspect

import com.cd18.domain.metrics.enums.ActionType
import com.cd18.domain.metrics.enums.TargetType
import com.cd18.domain.metrics.repository.UserActionLogRepository
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class LogUserAction(
    val actionType: ActionType,
    val targetType: TargetType,
    // targetId를 가져오기 위한 파라미터 이름
    val targetIdKey: String = "",
    val description: String = "",
)

@Aspect
@Component
class LogUserActionAspect(
    private val userActionLogRepository: UserActionLogRepository,
) {
    @Around("@annotation(logUserAction)")
    fun logUserAction(
        joinPoint: ProceedingJoinPoint,
        logUserAction: LogUserAction,
    ): Any? {
        val userId = null // TODO : 사용자 정보 가져오기
        val actionType = logUserAction.actionType
        val targetType = logUserAction.targetType
        val targetId = extractTargetId(joinPoint, logUserAction.targetIdKey)
        val description = logUserAction.description

        return try {
            val result = joinPoint.proceed()
            saveLog(userId, actionType, targetType, targetId, "$description - Success")
            result
        } catch (e: Exception) {
            saveLog(userId, actionType, targetType, targetId, "$description - Error: ${e.message}")
            throw e
        }
    }

    private fun extractTargetId(
        joinPoint: ProceedingJoinPoint,
        targetIdKey: String,
    ): Long? {
        if (targetIdKey.isBlank()) return null

        val args = joinPoint.args
        val methodSignature = joinPoint.signature as? MethodSignature ?: return null
        val parameterNames = methodSignature.parameterNames

        return args.getOrNull(parameterNames.indexOf(targetIdKey)) as? Long
    }

    private fun saveLog(
        userId: Long?,
        actionType: ActionType,
        targetType: TargetType,
        targetId: Long?,
        actionDetail: String,
    ) {
        userActionLogRepository.save(
            userId = userId,
            actionType = actionType,
            targetId = targetId,
            targetType = targetType,
            actionDetail = actionDetail,
        )
    }
}
