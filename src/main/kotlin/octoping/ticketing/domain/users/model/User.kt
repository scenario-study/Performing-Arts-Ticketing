package octoping.ticketing.domain.users.model

class User(
    id: Long = 0,
    username: String,
    password: String,
) {
    private val _id: Long
    private val _username: Username
    private var _password: Password

    init {
        this._id = id
        this._username = Username(username)
        this._password = Password(password)
    }

    val id: Long
        get() = _id

    val username: String
        get() = _username.value

    val password: String
        get() = _password.value

    fun changePassword(password: String) {
        this._password = Password(password)
    }
}
