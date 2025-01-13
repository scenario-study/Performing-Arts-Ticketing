package com.hunhui.ticketworld.domain.performance

/** 공연 상태
 * @property PRIVATE 비공개(조회 불가, 예매 불가)
 * @property PUBLIC 공개(조회 가능, 예매 가능)
 * @property CLOSED 종료(조회 가능, 예매 불가)
 * */
enum class PerformanceStatus(
    val canView: Boolean,
    val canReserve: Boolean
) {
    PRIVATE(false, false),
    PUBLIC(true, true),
    CLOSED(true, false)
}
