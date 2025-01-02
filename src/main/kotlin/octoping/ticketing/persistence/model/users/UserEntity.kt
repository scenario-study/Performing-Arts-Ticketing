package octoping.ticketing.persistence.model.users

import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import octoping.ticketing.domain.users.model.User
import octoping.ticketing.persistence.common.BaseEntity

@Entity
class UserEntity(
    @Column(name = "username", nullable = false)
    var username: String,
    @Embedded
    var password: String,
) : BaseEntity() {
    companion object {
        fun from(user: User) {
            throw NotImplementedError()
            // return UserEntity( )
        }
    }
}
