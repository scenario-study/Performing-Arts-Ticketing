package octoping.ticketing.domain.exception

abstract class DomainException(
    override val message: String,
) : RuntimeException(message)
