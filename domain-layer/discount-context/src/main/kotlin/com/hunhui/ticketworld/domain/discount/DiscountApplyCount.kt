package com.hunhui.ticketworld.domain.discount

interface DiscountApplyCount {
    val type: DiscountApplyCountType
    val amount: Int?

    fun canApply(count: Int): Boolean
}

object DiscountApplyCountFactory {
    fun create(
        type: DiscountApplyCountType,
        amount: Int?,
    ): DiscountApplyCount =
        when (type) {
            DiscountApplyCountType.MAX -> DiscountApplyMax(amount!!)
            DiscountApplyCountType.MULTIPLE -> DiscountApplyMultiple(amount!!)
            DiscountApplyCountType.SELF -> DiscountApplySelf
            DiscountApplyCountType.INF -> DiscountApplyInf
        }
}

/** 최대 {amount}매 적용 가능 */
data class DiscountApplyMax(
    override val amount: Int,
) : DiscountApplyCount {
    override val type: DiscountApplyCountType = DiscountApplyCountType.MAX

    override fun canApply(count: Int): Boolean = count <= amount
}

/** {amount}의 배수만 적용 가능 */
data class DiscountApplyMultiple(
    override val amount: Int,
) : DiscountApplyCount {
    override val type: DiscountApplyCountType = DiscountApplyCountType.MULTIPLE

    override fun canApply(count: Int): Boolean = count % amount == 0
}

/** 본인만 적용 가능(최대 1매) */
data object DiscountApplySelf : DiscountApplyCount {
    override val type: DiscountApplyCountType = DiscountApplyCountType.SELF
    override val amount: Int? = null

    override fun canApply(count: Int): Boolean = count == 1
}

/** 제한 없음 */
data object DiscountApplyInf : DiscountApplyCount {
    override val type: DiscountApplyCountType = DiscountApplyCountType.INF
    override val amount: Int? = null

    override fun canApply(count: Int): Boolean = true
}
