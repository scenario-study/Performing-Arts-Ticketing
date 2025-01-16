package com.hunhui.ticketworld.domain.seat.exception

import com.hunhui.ticketworld.common.error.BusinessException

class InvalidSeatException(
    errorCode: SeatErrorCode,
) : BusinessException(errorCode)
