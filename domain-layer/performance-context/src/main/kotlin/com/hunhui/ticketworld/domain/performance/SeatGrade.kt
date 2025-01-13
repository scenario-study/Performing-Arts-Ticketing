package com.hunhui.ticketworld.domain.performance

import com.hunhui.ticketworld.common.vo.Money
import java.util.UUID

class SeatGrade (
    val id: UUID,
    val gradeName: String,
    val price: Money,
)
