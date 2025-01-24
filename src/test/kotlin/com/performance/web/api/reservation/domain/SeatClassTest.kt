package com.performance.web.api.reservation.domain

import com.performance.web.api.common.domain.Money
import com.performance.web.api.discount.domain.DiscountFactor
import com.performance.web.api.discount.domain.NoneDiscountPolicy
import com.performance.web.api.fixtures.DiscountPolicyFixture
import com.performance.web.api.fixtures.SeatClassFixture
import com.performance.web.api.fixtures.SeatFixture
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class SeatClassTest {

    @Test
    fun `요청된 할인 정책이 없으면 예외를 반환한다`() {
        //given
        val seatClass = SeatClassFixture.create(
            discountPolicies = listOf(
                DiscountPolicyFixture.createPercent(id = 1L),
                DiscountPolicyFixture.createPercent(id = 2L),
            ),
        )
        //when
        //then
        assertThatThrownBy {
            seatClass.issueTicket(
                seat = SeatFixture.create(),
                discountPolicyId = 3L,
                discountFactor = DiscountFactor(LocalDateTime.now(), 10),
            )
        }.hasMessageContaining("잘못된 discountPolicyId 입니다")
    }

    @Test
    fun `할인 정책을 적용해 티켓을 생성한다`() {
        //given
        val seatClass = SeatClassFixture.create(
            price = Money.of(10000),
            classType = "VIP",
            discountPolicies = listOf(
                DiscountPolicyFixture.createPercent(
                    id = 1L,
                    percent = 0.5,
                ),
            ),
        )
        //when
        val ticket = seatClass.issueTicket(
            seat = SeatFixture.create(),
            discountPolicyId = 1L,
            discountFactor = DiscountFactor(LocalDateTime.now(), 10),
        )

        //then
        assertThat(ticket.getTotalAmount()).isEqualTo(Money.of(5000))
    }

    @Test
    fun `할인 정책을 입력하지 않으면 비할인 정책을 적용한다`() {
        //given
        val seatClass = SeatClassFixture.create(
            price = Money.of(10000),
            classType = "VIP",
            discountPolicies = listOf(
                DiscountPolicyFixture.createPercent(
                    id = 1L,
                    percent = 0.5,
                ),
            ),
        )
        //when
        val ticket = seatClass.issueTicket(
            seat = SeatFixture.create(),
            discountPolicyId = null,
            discountFactor = DiscountFactor(LocalDateTime.now(), 10),
        )

        //then
        assertThat(ticket.getTotalAmount()).isEqualTo(Money.of(10000))
        assertThat(ticket.getAppliedDiscountPolicy()).isInstanceOf(NoneDiscountPolicy::class.java)
    }
}
