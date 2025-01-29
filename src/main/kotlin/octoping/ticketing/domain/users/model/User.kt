package octoping.ticketing.domain.users.model

class User(
    id: Long = 0,
    username: String,
) {
    private val _id: Long = id
    private val _username = Username(username)

    val id: Long
        get() = _id

    val username: String
        get() = _username.value

    fun isNew(): Boolean {
        return this._id == 0L
    }
}
