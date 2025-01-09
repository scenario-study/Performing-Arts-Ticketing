package octoping.ticketing.domain.artview.repository

import octoping.ticketing.domain.artview.model.ArtView
import java.time.LocalDateTime

interface ArtViewRepository {
    fun save(artView: ArtView): ArtView

    // TODO: userId가 있으면 id가 우선, 없으면 IP로 체크
    fun findByUserAndDate(userIP: String, userId: Long?, date: LocalDateTime): ArtView?

    fun getViewCountByArtBetween(artId: Long, startDate: LocalDateTime, endDate: LocalDateTime): Long
}