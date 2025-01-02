package octoping.ticketing.domain.users.model

data class Password(
    val value: String,
) {
    init {
        require(value.length >= 2) { "비밀번호는 2글자 이상이어야 합니다" }
    }

    fun matches(password: String) = value == password
}
