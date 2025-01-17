package com.hunhui.ticketworld.domain.user

import java.util.UUID

interface UserRepository {
    fun getById(id: UUID): User

    fun save(user: User)
}
