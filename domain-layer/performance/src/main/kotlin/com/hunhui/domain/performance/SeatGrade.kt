package com.hunhui.domain.performance

import com.hunhui.common.vo.Money
import java.util.UUID

class SeatGrade (
    val id: UUID,
    val gradeName: String,
    val price: Money,
)
