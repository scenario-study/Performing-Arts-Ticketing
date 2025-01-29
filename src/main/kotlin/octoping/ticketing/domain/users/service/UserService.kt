package octoping.ticketing.domain.users.service

import octoping.ticketing.domain.users.model.User
import octoping.ticketing.domain.users.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun createUser(username: String) {
        val user = User(username = username)
        userRepository.save(user)
    }
}
