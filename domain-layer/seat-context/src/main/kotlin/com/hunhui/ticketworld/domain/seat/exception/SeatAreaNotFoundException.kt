package com.hunhui.ticketworld.domain.seat.exception

import com.hunhui.ticketworld.common.error.BusinessException

class SeatAreaNotFoundException : BusinessException(SeatErrorCode.AREA_NOT_FOUND)
