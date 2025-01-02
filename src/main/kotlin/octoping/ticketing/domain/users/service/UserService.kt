package octoping.ticketing.domain.users.service

import octoping.ticketing.domain.users.model.User
import octoping.ticketing.domain.users.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    @Transactional
    fun createUser() {
        val user = User(
            username = "octoping",
            password = "password",
        )
    }
}
