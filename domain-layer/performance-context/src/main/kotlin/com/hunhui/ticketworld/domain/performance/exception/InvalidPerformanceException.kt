package com.hunhui.ticketworld.domain.performance.exception

import com.hunhui.ticketworld.common.error.BusinessException

class InvalidPerformanceException(
    performanceErrorCode: PerformanceErrorCode,
) : BusinessException(performanceErrorCode)
