package octoping.ticketing.domain.users.repository

import octoping.ticketing.domain.users.model.User
import org.springframework.stereotype.Repository

@Repository
interface UserRepository {
    fun findById(id: Long): User?

    fun save(user: User): User
}
