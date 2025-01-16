package com.hunhui.ticketworld.domain.performance.exception

import com.hunhui.ticketworld.common.error.BusinessException

class InvalidPerformanceRoundException(
    performanceRoundErrorCode: PerformanceRoundErrorCode,
) : BusinessException(performanceRoundErrorCode)
