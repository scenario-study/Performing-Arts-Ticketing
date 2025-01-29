package com.cd18.application.aspect

import com.cd18.application.metrics.UserActionLogService
import com.cd18.domain.metrics.annotation.LogUserAction
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component

@Aspect
@Component
class LogUserActionAspect(
    private val userActionLogService: UserActionLogService,
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
            userActionLogService.logAction(userId, actionType, targetType, targetId, "$description - Success")
            result
        } catch (e: Exception) {
            userActionLogService.logAction(userId, actionType, targetType, targetId, "$description - Error: ${e.message}")
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
}
