package octoping.ticketing.persistence.exception

abstract class PersistenceException(
    override val message: String,
) : RuntimeException(message)
