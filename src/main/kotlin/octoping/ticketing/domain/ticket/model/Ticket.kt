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
    private val _boughtPrice = boughtPrice
    private val _boughtUserId = boughtUserId
    private var _isRefunded = isRefunded
    private val _boughtAt = boughtAt
    private val _refundedAt = refundedAt

    val id: Long
        get() = _id

    val artId: Long
        get() = _artId

    val originalPrice: Long
        get() = _originalPrice

    val boughtPrice: Long
        get() = _boughtPrice

    val boughtUserId: Long
        get() = _boughtUserId

    val isRefunded: Boolean
        get() = _isRefunded

    val boughtAt: LocalDateTime
        get() = _boughtAt

    val refundedAt: LocalDateTime?
        get() = _refundedAt

    init {
        if (originalPrice < 0 || boughtPrice < 0) {
            throw ValidationException("가격은 음수가 될 수 없습니다")
        }
    }
}
