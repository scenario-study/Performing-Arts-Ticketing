package com.cd18.domain.metrics.annotation

import com.cd18.domain.metrics.enums.ActionType
import com.cd18.domain.metrics.enums.TargetType

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class LogUserAction(
    val actionType: ActionType,
    val targetType: TargetType,
    // targetId를 가져오기 위한 파라미터 이름
    val targetIdKey: String = "",
    val description: String = "",
)
