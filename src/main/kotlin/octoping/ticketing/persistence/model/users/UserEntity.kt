package octoping.ticketing.persistence.model.users

import jakarta.persistence.Column
import jakarta.persistence.Entity
import octoping.ticketing.domain.users.model.User
import octoping.ticketing.persistence.common.BaseEntity

@Entity
class UserEntity(
    id: Long = 0,
    @Column(name = "username", nullable = false)
    var username: String,
) : BaseEntity(id) {
    fun toUser() =
        User(
            id = this.id,
            username = this.username,
        )

    companion object {
        fun from(user: User) =
            UserEntity(
                id = user.id,
                username = user.username,
            )
    }
}
