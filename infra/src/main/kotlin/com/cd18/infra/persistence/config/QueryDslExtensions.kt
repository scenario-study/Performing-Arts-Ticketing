package com.cd18.infra.persistence.config

import com.cd18.domain.common.page.PageParam
import com.querydsl.jpa.impl.JPAQuery
import org.springframework.data.domain.Pageable

fun <T> JPAQuery<T>.applyPagination(pageParam: PageParam): JPAQuery<T> {
    val pageable: Pageable = Pageable.ofSize(pageParam.size).withPage(pageParam.page)
    return this.offset(pageable.offset)
        .limit(pageable.pageSize.toLong())
}
