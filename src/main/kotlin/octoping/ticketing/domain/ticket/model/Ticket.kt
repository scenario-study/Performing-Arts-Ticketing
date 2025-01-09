package octoping.ticketing.domain.ticket.model

import octoping.ticketing.domain.exception.ValidationException
import java.time.LocalDateTime

class Ticket(
    id: Long = 0,
    artId: Long,
    originalPrice: Long,
    boughtPrice: Long,
    boughtUserId: Long,
    isRefunded: Boolean = false,
    boughtAt: LocalDateTime = LocalDateTime.now(),
    refundedAt: LocalDateTime? = null,
) {
    private val _id = id
    private val _artId = artId
    private val _originalPrice = originalPrice
    private val _price = boughtPrice
    private val _boughtUserId = boughtUserId
    private var _isRefunded = isRefunded
    private val _boughtAt = boughtAt
    private val _refundedAt = refundedAt

    init {
        if (originalPrice < 0 || boughtPrice < 0) {
            throw ValidationException("가격은 음수가 될 수 없습니다")
        }
    }
}