package com.hunhui.ticketworld.domain.discount

sealed class DiscountApplyCount(
    val type: DiscountApplyCountType,
) {
    abstract val amount: Int?

    abstract fun canApply(count: Int): Boolean

    companion object {
        fun create(
            type: DiscountApplyCountType,
            amount: Int?,
        ): DiscountApplyCount =
            when (type) {
                DiscountApplyCountType.MAX -> Max(amount!!)
                DiscountApplyCountType.MULTIPLE -> Multiple(amount!!)
                DiscountApplyCountType.SELF -> Self
                DiscountApplyCountType.INF -> Inf
            }
    }

    /** 최대 {amount}매 적용 가능 */
    data class Max(
        override val amount: Int,
    ) : DiscountApplyCount(DiscountApplyCountType.MAX) {
        override fun canApply(count: Int): Boolean = count <= amount
    }

    /** {amount}의 배수만 적용 가능 */
    data class Multiple(
        override val amount: Int,
    ) : DiscountApplyCount(DiscountApplyCountType.MULTIPLE) {
        override fun canApply(count: Int): Boolean = count % amount == 0
    }

    /** 본인만 적용 가능(최대 1매) */
    data object Self : DiscountApplyCount(DiscountApplyCountType.SELF) {
        override val amount: Int? = null

        override fun canApply(count: Int): Boolean = count == 1
    }

    /** 제한 없음 */
    data object Inf : DiscountApplyCount(DiscountApplyCountType.INF) {
        override val amount: Int? = null

        override fun canApply(count: Int): Boolean = true
    }
}
