package octoping.ticketing.domain.ticket.model

import java.time.LocalDateTime

class Ticket(
    id: Long = 0,
    originalPrice: Long,
    boughtPrice: Long,
    boughtUserId: Long,
    isRefunded: Boolean = false,
    boughtAt: LocalDateTime = LocalDateTime.now(),
    refundedAt: LocalDateTime? = null,
) {
    private val _id = id
    private val _originalPrice = originalPrice
    private val _boughtPrice = boughtPrice
    private val _boughtUserId = boughtUserId
    private var _isRefunded = isRefunded
    private val _boughtAt = boughtAt
    private val _refundedAt = refundedAt
}