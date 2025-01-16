package com.hunhui.ticketworld.domain.performance.exception

import com.hunhui.ticketworld.common.error.BusinessException

class PerformanceNotFoundException : BusinessException(PerformanceErrorCode.NOT_FOUND)
